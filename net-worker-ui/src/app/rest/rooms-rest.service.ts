import {Injectable} from "@angular/core";
import {Room} from "../model/room";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class RoomsRestService {

  baseUrl: string = environment.apiUrl + "/room";

  constructor(private httpClient: HttpClient) {
  }

  getAllRooms(): Observable<Room[]> {
    return this.httpClient.get<Room[]>(this.baseUrl + "/all");
  }

  createRoom(room: Room): Observable<Room> {
    return this.httpClient.post<Room>(this.baseUrl, room);
  }

  updateRoom(room: Room): Observable<Room> {
    return this.httpClient.post<Room>(this.baseUrl, room);
  }

  deleteRoom(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }
}
