import {Injectable} from "@angular/core";
import {Meeting} from "../model/meeting";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class MeetingRestService {

  baseUrl: string = environment.apiUrl + "/meeting";

  constructor(private httpClient: HttpClient) {
  }

  getAllMeetings(): Observable<Meeting[]> {
    return this.httpClient.get<Meeting[]>(this.baseUrl + "/all");
  }

  createMeeting(meeting: Meeting): Observable<Meeting> {
    return this.httpClient.post<Meeting>(this.baseUrl, meeting);
  }

  updateMeeting(meeting: Meeting): Observable<Meeting> {
    return this.httpClient.post<Meeting>(this.baseUrl, meeting);
  }

  deleteMeeting(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }
}
