import {Injectable} from "@angular/core";
import {Room} from "../model/room";
import {Observable} from "rxjs";
import {RoomsRestService} from "../rest/rooms-rest.service";
import {share} from "rxjs/operators";

@Injectable()
export class RoomService {

  constructor(private roomsRest: RoomsRestService) {
  }

  getAllRooms(): Observable<Room[]> {
    return this.roomsRest.getAllRooms().pipe(share());
  }

  createRoom(room: Room): Observable<Room> {
    return this.roomsRest.createRoom(room).pipe(share());
  }
}
