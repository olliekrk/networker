package com.elemonated.networker.service;

import com.elemonated.networker.NetApplication;
import com.elemonated.networker.persistence.data.GoogleCalendar;
import com.elemonated.networker.persistence.repository.GoogleCalendarRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class GoogleCalendarObserverService implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        this.service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

    }

    private Calendar service;
    private Set<GoogleCalendar> calendars = new CopyOnWriteArraySet<>();
    private final GoogleCalendarRepository googleCalendarRepository;
    private final ActivityService activityService;

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    @Autowired
    private GoogleCalendarObserverService(GoogleCalendarRepository googleCalendarRepository,
                                          ActivityService activityService) {
        this.googleCalendarRepository = googleCalendarRepository;
        this.activityService = activityService;
        for (GoogleCalendar calendar: googleCalendarRepository.findAll()){
            calendars.add(calendar);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = NetApplication.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("online")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    @Scheduled(fixedRate = 5000)
    public void checkForUpdates() {
        if(service == null){
            return;
        }
        for (GoogleCalendar calendar : calendars) {
            try {
                checkForUpdatesInCalendar(calendar);
            } catch (IOException exception) {
                //TODO: log removing
                calendars.remove(calendar);
            }
        }
    }

    private void checkForUpdatesInCalendar(GoogleCalendar calendar) throws IOException {

        Calendar.Events.List request = service.events().list(calendar.getCalendarId());

        if(calendar.getSyncToken() != null){
            request.setSyncToken(calendar.getSyncToken());
        }

        String pageToken = null;
        Events events = null;
        do {
            request.setPageToken(pageToken);
            try {
                events = request.execute();
            } catch (GoogleJsonResponseException e) {
                if (e.getStatusCode() == 410) {
                    // A 410 status code, "Gone", indicates that the sync token is invalid.
                    System.out.println("Invalid sync token, clearing event store and re-syncing.");
                    updateCalendarSyncToken(calendar, null);
                    checkForUpdatesInCalendar(calendar);
                } else {
                    throw e;
                }
            }
            List<Event> items = events.getItems();

            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                DateTime end = event.getEnd().getDateTime();
                if (start == null) {
                    end = event.getEnd().getDate();
                }
                System.out.printf("eventName: %s\nmail: %s \nstart: (%s)\nend: %s\n",
                        event.getSummary(), event.getCreator().getEmail(), start, end);
            }
            pageToken = events.getNextPageToken();
        }while(pageToken != null);
        updateCalendarSyncToken(calendar, events.getNextSyncToken());
    }

    private String getCalendarIdFromHttp(String calendarName) throws Exception {
        String pageToken = null;
        do {
            CalendarList calendarList = service.calendarList().list().setPageToken(pageToken).execute();
            List<CalendarListEntry> items = calendarList.getItems();

            for (CalendarListEntry calendarListEntry : items) {
                if (calendarListEntry.getSummary().equals(calendarName)) {
                    return calendarListEntry.getId();
                }
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);
        throw new Exception("Calendar not found!");
    }

    private void updateCalendarSyncToken(GoogleCalendar calendar, String syncToken){
        calendar.setSyncToken(syncToken);
        googleCalendarRepository.save(calendar);
    }

    public GoogleCalendar subscribeCalendar(String calendarName) throws Exception {
        GoogleCalendar calendar = new GoogleCalendar(getCalendarIdFromHttp(calendarName), calendarName);
        if (calendars.add(calendar)) {
            googleCalendarRepository.save(calendar);
            checkForUpdatesInCalendar(calendar);
            return calendar;
        } else {
            throw new Exception("Could not add calendar.");
        }
    }

    public void deleteCalendar(String calendarName) throws Exception{
        for(GoogleCalendar calendar: calendars){
            if(calendar.getCalendarSummary().equals(calendarName)){
                googleCalendarRepository.deleteById(calendar.getCalendarId());
                calendars.remove(calendar);
                return;
            }
        }
        throw new Exception("Calendar not found");
    }
}
