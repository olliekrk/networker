import {Injectable} from "@angular/core";
import {Meeting} from "../model/meeting";
import {BehaviorSubject, Observable} from "rxjs";
import {MeetingRestService} from "../rest/meeting-rest.service";
import {share, take} from "rxjs/operators";

@Injectable()
export class RoomService {

  private rooms$: BehaviorSubject<Meeting[]> = new BehaviorSubject<Meeting[]>([]);

  constructor(private meetingsRest: MeetingRestService) {
    this.reloadMeetings();
  }

  reloadMeetings(): void {
    this.getAllMeetings().pipe(take(1)).subscribe(rooms => this.rooms$.next(rooms));
  }

  getRooms(): Observable<Meeting[]> {
    return this.rooms$.asObservable().pipe(share());
  }

  private getAllMeetings(): Observable<Meeting[]> {
    return this.meetingsRest.getAllMeetings().pipe(share());
  }


  createRoom(meeting: Meeting): Observable<Meeting> {
    return this.meetingsRest.createMeeting(meeting).pipe(share());
  }

  updateMeeting(meeting: Meeting): Observable<Meeting> {
    return this.meetingsRest.updateMeeting(meeting).pipe(share());
  }

  deleteMeeting(id: number): Observable<void> {
    return this.meetingsRest.deleteMeeting(id).pipe(share());
  }
}
