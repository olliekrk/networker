package com.elemonated.networker.rest;

import com.elemonated.networker.persistence.data.GoogleCalendar;
import com.elemonated.networker.service.GoogleCalendarObserverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/googleCalendar")
public class GoogleCalendarRest {
    private final GoogleCalendarObserverService googleCalendarObserverService;

    @Autowired
    private GoogleCalendarRest(GoogleCalendarObserverService googleCalendarObserverService) {
        this.googleCalendarObserverService = googleCalendarObserverService;
    }

    @PostMapping
    public GoogleCalendar addCalendar(@RequestParam String calendarName) {
        try {
            return googleCalendarObserverService.subscribeCalendar(calendarName);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage(), e);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCalendar(@RequestParam String calendarName) {
        try {
            googleCalendarObserverService.deleteCalendar(calendarName);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return new ResponseEntity<>(calendarName, HttpStatus.OK);
    }
}
