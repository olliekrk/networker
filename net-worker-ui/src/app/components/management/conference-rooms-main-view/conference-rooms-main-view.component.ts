import {Component, OnInit} from "@angular/core";
import {MatDialog} from "@angular/material";
import {RoomEditDialogComponent} from "../room-edit-dialog/room-edit-dialog.component";
import {filter, switchMap, take, tap} from "rxjs/operators";
import {RoomService} from "../../../services/room.service";
import {EditorMode} from "../../../model/utils";
import {Room} from "../../../model/room";
import {Observable} from "rxjs";

@Component({
  selector: "app-conference-rooms-main-view",
  templateUrl: "./conference-rooms-main-view.component.html",
  styleUrls: ["./conference-rooms-main-view.component.scss"]
})
export class ConferenceRoomsMainViewComponent implements OnInit {

  rooms$: Observable<Room[]>;

  constructor(private dialog: MatDialog,
              private roomsService: RoomService) {
  }

  ngOnInit() {
    this.rooms$ = this.roomsService.getRooms();
  }


  createRoomDialog(): void {
    const data = {
      mode: EditorMode.CREATE
    };

    this.dialog
      .open(RoomEditDialogComponent, {autoFocus: true, disableClose: true, data})
      .afterClosed()
      .pipe(
        filter(result => !!result), // nonEmpty
        switchMap(roomToSave => this.roomsService.createRoom(roomToSave)),
        tap(() => this.roomsService.reloadRooms())
      ).subscribe();
  }


}
