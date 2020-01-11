import {Component, Input} from "@angular/core";
import {Employee} from "../../../model/employee";
import {MatDialog} from "@angular/material";
import {EditorMode} from "../../../model/utils";
import {EmployeeEditDialogComponent} from "../employee-edit-dialog/employee-edit-dialog.component";
import {filter, switchMap, tap} from "rxjs/operators";
import {EmployeesService} from "../../../services/employees.service";

@Component({
  selector: "app-employee-info-bar",
  templateUrl: "./employee-info-bar.component.html",
  styleUrls: ["./employee-info-bar.component.scss"]
})
export class EmployeeInfoBarComponent {

  @Input() index: number;
  @Input() employee: Employee;

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
