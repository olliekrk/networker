import {Injectable} from "@angular/core";
import {GoogleCalendarId} from "../model/google";
import {BehaviorSubject, Observable} from "rxjs";
import {GoogleRestService} from "../rest/google-rest.service";
import {share, take} from "rxjs/operators";

@Injectable()
export class GoogleService {

  private calendarIds$: BehaviorSubject<GoogleCalendarId[]> = new BehaviorSubject<GoogleCalendarId[]>([]);

  constructor(private googleRestService: GoogleRestService) {
  }

  importCalendar(calendarId: GoogleCalendarId): Observable<GoogleCalendarId> {
    return this.googleRestService.importCalendar(calendarId);
  }

  reloadCalendars(): void {
    this.fetchCalendarIds().pipe(take(1)).subscribe(ids => this.calendarIds$.next(ids));
  }

  getCalendarIds(): Observable<GoogleCalendarId[]> {
    return this.calendarIds$.asObservable().pipe(share());
  }

  private fetchCalendarIds(): Observable<GoogleCalendarId[]> {
    return this.googleRestService.getCalendarIds();
  }
}
