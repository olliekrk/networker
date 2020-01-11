import {Component, OnInit} from "@angular/core";
import {MatDialog} from "@angular/material";
import {MeetingEditDialogComponent} from "../meeting-edit-dialog/meeting-edit-dialog.component";
import {filter, switchMap, take, tap} from "rxjs/operators";
import {MeetingService} from "../../../services/meeting.service";
import {EditorMode} from "../../../model/utils";
import {Meeting} from "../../../model/meeting";
import {Observable} from "rxjs";

@Component({
  selector: "app-meetings-main-view",
  templateUrl: "./meetings-main-view.component.html",
  styleUrls: ["./meetings-main-view.component.scss"]
})
export class MeetingsMainViewComponent implements OnInit {

  meetings$: Observable<Meeting[]>;
  constructor(private dialog: MatDialog,
              private meetingService: MeetingService) {
  }

  ngOnInit() {
    this.meetings$ = this.meetingService.getMeetings();
  }
  createMeetingDialog(): void {
    const data = {
      mode: EditorMode.CREATE
    };

    this.dialog
      .open(MeetingEditDialogComponent, {autoFocus: true, disableClose: true, data})
      .afterClosed()
      .pipe(
        filter(result => !!result), // nonEmpty
        switchMap(meetingToSave => this.meetingService.createMeeting(meetingToSave)),
        tap(() => this.meetingService.reloadMeetings())
      ).subscribe();
  }


}






