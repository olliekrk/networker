import {Component, Input} from "@angular/core";
import {Room} from "../../../model/room";
import {MatDialog} from "@angular/material";
import {EditorMode} from "../../../model/utils";
import {RoomEditDialogComponent} from "../room-edit-dialog/room-edit-dialog.component";
import {filter, switchMap, tap} from "rxjs/operators";
import {RoomService} from "../../../services/room.service";

@Component({
  selector: "app-room-info-bar",
  templateUrl: "./room-info-bar.component.html",
  styleUrls: ["./room-info-bar.component.scss"]
})
export class RoomInfoBarComponent {

  @Input() index: number;
  @Input() room: Room;

  constructor(private dialog: MatDialog,
              private roomService: RoomService) {
  }

  editRoomDialog(): void {
    const data = {
      mode: EditorMode.EDIT,
      room: this.room,
    };

    this.dialog
      .open(RoomEditDialogComponent, {autoFocus: true, disableClose: true, data})
      .afterClosed()
      .pipe(
        filter(result => !!result), // nonEmpty
        switchMap(roomToSave => this.roomService.updateRoom(roomToSave)),
        tap(() => this.roomService.reloadRooms())
      ).subscribe();
  }

  deleteRoom(): void {
    this.roomService.deleteRoom(this.room.id)
      .subscribe(() => this.roomService.reloadRooms());
  }

}
