package com.elemonated.networker.service;

import com.elemonated.networker.NetApplication;
import com.elemonated.networker.persistence.data.Activity;
import com.elemonated.networker.persistence.data.GoogleCalendar;
import com.elemonated.networker.persistence.repository.GoogleCalendarRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpStatusCodes;
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
import com.google.common.collect.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Component
public class GoogleCalendarObserverService {
    @Autowired
    private GoogleCalendarObserverService(GoogleCalendarRepository googleCalendarRepository,
                                          ActivityService activityService,
                                          GoogleAuthorizationService googleAuthorizationService,
                                          EmployeeService employeeService) {
        this.googleCalendarRepository = googleCalendarRepository;
        this.activityService = activityService;
        this.googleAuthorizationService = googleAuthorizationService;
        this.employeeService = employeeService;
        for (GoogleCalendar calendar: googleCalendarRepository.findAll()){
            calendars.add(calendar);
        }
    }

    private final GoogleAuthorizationService googleAuthorizationService;
    private Calendar service;
    private Set<GoogleCalendar> calendars = new CopyOnWriteArraySet<>();
    private final GoogleCalendarRepository googleCalendarRepository;
    private final ActivityService activityService;
    private final EmployeeService employeeService;

    public void setService() throws Exception{
        this.service = googleAuthorizationService.getCalendarService();
    }

    public Calendar getService(){
        return service;
    }

    @Scheduled(fixedRate = 5000)
    public void checkForUpdates() {
        if(service == null){
            try{
                this.setService();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
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

    public List<String> getAllCalendarIds() {
        return Streams.stream(googleCalendarRepository.findAll()).map(GoogleCalendar::getCalendarId).collect(Collectors.toList());
    }

    private void checkForUpdatesInCalendar(GoogleCalendar calendar) throws IOException {

        Calendar.Events.List request = service.events().list(calendar.getCalendarId());

        if (calendar.getSyncToken() != null) {
            request.setSyncToken(calendar.getSyncToken());
        }

        String pageToken = null;
        Events events = null;
        do {
            request.setPageToken(pageToken);
            try {
                events = request.execute();
            } catch (GoogleJsonResponseException e) {
                if (e.getStatusCode() == HttpStatus.GONE.value()) {
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
                if (end == null) {
                    end = event.getEnd().getDate();
                }
                System.out.printf("eventName: %s\nmail: %s \nstart: (%s)\nend: %s\n",
                        event.getSummary(), event.getCreator().getEmail(), start, end);
                ///////////////////////////////////////////////////////////////////////
                Activity activity = new Activity();
                activity.setStartTimestamp(new Timestamp(start.getValue()));
                activity.setEndTimestamp(new Timestamp(end.getValue()));
                activity.setEmployee(employeeService.findByEmail(event.getCreator().getEmail()));
                activityService.saveActivity(activity);



            }
            pageToken = events.getNextPageToken();
        } while (pageToken != null);
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

    private void updateCalendarSyncToken(GoogleCalendar calendar, String syncToken) {
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

    public void deleteCalendar(String calendarId) throws Exception {
        for(GoogleCalendar calendar: calendars) {
            if(calendar.getCalendarId().equals(calendarId))
                calendars.remove(calendar);
        }
        googleCalendarRepository.deleteById(calendarId);

    }
}
