import {Injectable} from "@angular/core";
import {Room} from "../model/room";
import {BehaviorSubject, Observable} from "rxjs";
import {RoomsRestService} from "../rest/rooms-rest.service";
import {share, take} from "rxjs/operators";
import {Employee, EmployeeId} from "../model/employee";

@Injectable()
export class RoomService {

  private rooms$: BehaviorSubject<Room[]> = new BehaviorSubject<Room[]>([]);

  constructor(private roomsRest: RoomsRestService) {
    this.reloadRooms();
  }

  reloadRooms(): void {
    this.getAllRooms().pipe(take(1)).subscribe(rooms => this.rooms$.next(rooms));
  }

  getRooms(): Observable<Room[]> {
    return this.rooms$.asObservable().pipe(share());
  }

  private getAllRooms(): Observable<Room[]> {
    return this.roomsRest.getAllRooms().pipe(share());
  }


  createRoom(room: Room): Observable<Room> {
    return this.roomsRest.createRoom(room).pipe(share());
  }

  updateRoom(room: Room): Observable<Room> {
    return this.roomsRest.updateRoom(room).pipe(share());
  }

  deleteRoom(id: number): Observable<void> {
    return this.roomsRest.deleteRoom(id).pipe(share());
  }
}
