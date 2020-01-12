import {Component, Input} from "@angular/core";
import {MatDialog} from "@angular/material";
import {MeetingEditDialogComponent} from "../meeting-edit-dialog/meeting-edit-dialog.component";
import {filter, switchMap, tap} from "rxjs/operators";
import {Meeting} from "../../../../model/meeting";
import {MeetingService} from "../../../../services/meeting.service";
import {EditorMode} from "../../../../model/utils";

@Component({
  selector: "app-meeting-info-bar",
  templateUrl: "./meeting-info-bar.component.html",
  styleUrls: ["./meeting-info-bar.component.scss"]
})
export class MeetingInfoBarComponent {

  @Input() index: number;
  @Input() meeting: Meeting;

  constructor(private dialog: MatDialog,
              private meetingService: MeetingService) {
  }

  editMeetingDialog(): void {
    const data = {
      mode: EditorMode.EDIT,
      meeting: this.meeting,
    };

    this.dialog
      .open(MeetingEditDialogComponent, {autoFocus: true, disableClose: true, data})
      .afterClosed()
      .pipe(
        filter(result => !!result), // nonEmpty
        switchMap(meetingToSave => this.meetingService.updateMeeting(meetingToSave)),
        tap(() => this.meetingService.reloadMeetings())
      ).subscribe();
  }

  deleteMeeting(): void {
    this.meetingService.deleteMeeting(this.meeting.id)
      .subscribe(() => this.meetingService.reloadMeetings());
  }

}
