import {Component, Input} from "@angular/core";
import {MatDialog} from "@angular/material";
import {EmployeeEditDialogComponent} from "../employee-edit-dialog/employee-edit-dialog.component";
import {filter, switchMap, tap} from "rxjs/operators";
import {EditorMode} from "../../../../model/utils";
import {Employee, getReadableEmployeeName} from "../../../../model/employee";
import {EmployeesService} from "../../../../services/employees.service";

@Component({
  selector: "app-employee-info-bar",
  templateUrl: "./employee-info-bar.component.html",
  styleUrls: ["./employee-info-bar.component.scss"]
})
export class EmployeeInfoBarComponent {

  @Input() index: number;
  @Input() employee: Employee;
  readonly getReadableEmployeeName = getReadableEmployeeName;

  constructor(private dialog: MatDialog,
              private employeesService: EmployeesService) {
  }

  editEmployeeDialog(): void {
    const data = {
      mode: EditorMode.EDIT,
      employee: this.employee,
    };

    this.dialog
      .open(EmployeeEditDialogComponent, {autoFocus: true, disableClose: true, data: data})
      .afterClosed()
      .pipe(
        filter(result => !!result), // nonEmpty
        switchMap(employeeToSave => this.employeesService.updateEmployee(employeeToSave)),
        tap(() => this.employeesService.reloadEmployees())
      ).subscribe();
  }

  deleteEmployee(): void {
    this.employeesService.deleteEmployee(this.employee.id)
      .subscribe(() => this.employeesService.reloadEmployees());
  }

}
