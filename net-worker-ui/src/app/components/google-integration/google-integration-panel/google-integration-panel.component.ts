import {Component, OnInit} from "@angular/core";
import {MatDialog} from "@angular/material";
import {filter, switchMap} from "rxjs/operators";
import {GoogleCalendarId} from "../../../model/google";
import {Observable} from "rxjs";
import {GoogleService} from "../../../services/google.service";
import {GoogleCalendarImportDialogComponent} from "../google-calendar-import-dialog/google-calendar-import-dialog.component";

@Component({
  selector: "app-google-integration-panel",
  templateUrl: "./google-integration-panel.component.html",
  styleUrls: ["./google-integration-panel.component.scss"]
})
export class GoogleIntegrationPanelComponent implements OnInit {

  calendars$: Observable<GoogleCalendarId[]>;

  constructor(private dialog: MatDialog,
              private googleService: GoogleService) {
  }

  ngOnInit() {
    this.calendars$ = this.googleService.getCalendarIds();
  }

  importCalendarDialog(): void {
    this.dialog
      .open(GoogleCalendarImportDialogComponent, {autoFocus: true})
      .afterClosed()
      .pipe(
        filter(result => !!result),
        switchMap(calendarId => this.googleService.importCalendar(calendarId))
      ).subscribe(() => this.googleService.reloadCalendars());
  }

  deleteCalendar(calendarId: GoogleCalendarId) {
    this.googleService.deleteCalendar(calendarId)
      .subscribe(() => this.googleService.reloadCalendars());
  }
}
