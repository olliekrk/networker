package com.elemonated.networker.persistence.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "syncToken")
public class GoogleCalendar {
    public GoogleCalendar(String calendarId, String calendarSummary){
        this.calendarId = calendarId;
        this.calendarSummary = calendarSummary;
    }
    @Id
    private String calendarId;
    private String calendarSummary;
    private String syncToken = null;


}
