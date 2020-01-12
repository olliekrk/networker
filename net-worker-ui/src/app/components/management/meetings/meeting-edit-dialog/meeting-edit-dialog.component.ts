import {Component, Inject, OnInit} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Meeting} from "../../../../model/meeting";
import {Observable} from "rxjs";
import {Employee, getReadableEmployeeName} from "../../../../model/employee";
import {Room} from "../../../../model/room";
import {EmployeesService} from "../../../../services/employees.service";
import {RoomService} from "../../../../services/room.service";
import {EditorMode} from "../../../../model/utils";

@Component({
  selector: "app-meeting-edit-dialog",
  templateUrl: "./meeting-edit-dialog.component.html",
  styleUrls: ["./meeting-edit-dialog.component.scss"]
})
export class MeetingEditDialogComponent implements OnInit {
  meetingForm: FormGroup;
  meetingData?: Meeting;
  dialogTitle: string;
  employees$: Observable<Employee[]>;
  rooms$: Observable<Room[]>;

  constructor(private fb: FormBuilder,
              private dialogRef: MatDialogRef<MeetingEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data,
              private employeeService: EmployeesService,
              private roomService: RoomService) {
    this.dialogTitle = data.mode == EditorMode.CREATE ? "Create new meeting" : "Edit meeting";
    this.meetingData = data.meeting || {};
    this.employees$ = employeeService.getAllEmployees();
    this.rooms$ = roomService.getAllRooms();
  }

  getReadableEmployeeName = getReadableEmployeeName;

  ngOnInit() {
    this.meetingForm = this.fb.group({
      id: [this.meetingData.id],
      employeeMeetingLeaderID: [this.meetingData.employeeMeetingLeaderID],
      subject: [this.meetingData.subject],
      employeesParticipantsID: [this.meetingData.employeesParticipantsID],
      roomID: [this.meetingData.roomID],
    });
  }

  save() {
    this.dialogRef.close(Object.assign({id: this.meetingData.id}, this.meetingForm.value));
  }

  close() {
    this.dialogRef.close();
  }

}










