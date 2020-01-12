import {Component, Inject, OnInit} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";
import {EditorMode} from "../../../../model/utils";
import {Employee} from "../../../../model/employee";

@Component({
  selector: "app-employee-edit-dialog",
  templateUrl: "./employee-edit-dialog.component.html",
  styleUrls: ["./employee-edit-dialog.component.scss"]
})
export class EmployeeEditDialogComponent implements OnInit {

  employeeForm: FormGroup;
  employeeData?: Employee;
  dialogTitle: string;

  constructor(private fb: FormBuilder,
              private dialogRef: MatDialogRef<EmployeeEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data) {
    this.dialogTitle = data.mode === EditorMode.CREATE ? "Create new employee" : "Edit employee";
    this.employeeData = data.employee || {};
  }

  ngOnInit() {
    this.employeeForm = this.fb.group({
      firstName: [this.employeeData.firstName],
      lastName: [this.employeeData.lastName],
      email: [this.employeeData.email],
      phone: [this.employeeData.phone],
    });
  }

  save() {
    this.dialogRef.close(Object.assign({id: this.employeeData.id}, this.employeeForm.value));
  }

  close() {
    this.dialogRef.close();
  }

}
