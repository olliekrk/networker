import {Component, Inject, Input, OnInit} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: "app-google-calendar-import-dialog",
  templateUrl: "./google-calendar-import-dialog.component.html",
  styleUrls: ["./google-calendar-import-dialog.component.scss"]
})
export class GoogleCalendarImportDialogComponent {

  @Input() dialogTitle = "Watch a calendar";

  inputId: string;

  constructor(private dialogRef: MatDialogRef<GoogleCalendarImportDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data) {
  }

  save() {
    this.dialogRef.close(this.inputId);
  }

  close() {
    this.dialogRef.close();
  }

}
