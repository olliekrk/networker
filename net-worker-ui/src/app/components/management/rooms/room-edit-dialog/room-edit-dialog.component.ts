import {Component, Inject, OnInit} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Room} from "../../../../model/room";
import {EditorMode} from "../../../../model/utils";

@Component({
  selector: "app-room-edit-dialog",
  templateUrl: "./room-edit-dialog.component.html",
  styleUrls: ["./room-edit-dialog.component.scss"]
})
export class RoomEditDialogComponent implements OnInit {

  roomForm: FormGroup;
  roomData?: Room;
  dialogTitle: string;

  constructor(private fb: FormBuilder,
              private dialogRef: MatDialogRef<RoomEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data) {
    // tslint:disable-next-line:triple-equals
    this.dialogTitle = data.mode == EditorMode.CREATE ? "Create new room" : "Edit room";
    this.roomData = data.room || {};
  }

  ngOnInit() {
    this.roomForm = this.fb.group({
      id: [this.roomData.id],
      name: [this.roomData.name],
      description: [this.roomData.description],
      capacity: [this.roomData.capacity],
    });
  }

  save() {
    this.dialogRef.close(Object.assign({id: this.roomData.id}, this.roomForm.value));
  }

  close() {
    this.dialogRef.close();
  }

}
