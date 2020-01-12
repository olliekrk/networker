import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {GoogleCalendarId} from "../model/google";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class GoogleRestService {

  readonly calendarUrl = environment.apiUrl + "/googleCalendar";

  constructor(private httpClient: HttpClient) {
  }

  importCalendar(calendarId: GoogleCalendarId): Observable<GoogleCalendarId> {
    return this.httpClient.post<GoogleCalendarId>(`${this.calendarUrl}`, calendarId);
  }

  getCalendarIds(): Observable<GoogleCalendarId[]> {
    return this.httpClient.get<GoogleCalendarId[]>(`${this.calendarUrl}/all`);
  }
}
