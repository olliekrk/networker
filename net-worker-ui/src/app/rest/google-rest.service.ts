import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {GoogleCalendarId} from "../model/google";
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable()
export class GoogleRestService {

  readonly calendarUrl = environment.apiUrl + "/googleCalendar";

  constructor(private httpClient: HttpClient) {
  }

  importCalendar(calendarId: GoogleCalendarId): Observable<GoogleCalendarId> {
    const params = new HttpParams().set("calendarName", calendarId);
    return this.httpClient.post<GoogleCalendarId>(`${this.calendarUrl}`, {}, {params});
  }

  getCalendarIds(): Observable<GoogleCalendarId[]> {
    return this.httpClient.get<GoogleCalendarId[]>(`${this.calendarUrl}/all`);
  }
}
