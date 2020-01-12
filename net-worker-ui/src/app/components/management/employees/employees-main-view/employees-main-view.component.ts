import {Component, OnInit} from "@angular/core";
import {MatDialog} from "@angular/material";
import {EmployeeEditDialogComponent} from "../employee-edit-dialog/employee-edit-dialog.component";
import {filter, switchMap, take, tap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Employee} from "../../../../model/employee";
import {EmployeesService} from "../../../../services/employees.service";
import {EditorMode} from "../../../../model/utils";

@Component({
  selector: "app-employees-main-view",
  templateUrl: "./employees-main-view.component.html",
  styleUrls: ["./employees-main-view.component.scss"]
})
export class EmployeesMainViewComponent implements OnInit {

  employees$: Observable<Employee[]>;

  constructor(private dialog: MatDialog,
              private employeesService: EmployeesService) {
  }

  ngOnInit() {
    this.employees$ = this.employeesService.getEmployees();
  }

  createEmployeeDialog(): void {
    const data = {
      mode: EditorMode.CREATE
    };

    this.dialog
      .open(EmployeeEditDialogComponent, {autoFocus: true, disableClose: true, data: data})
      .afterClosed()
      .pipe(
        filter(result => !!result), // nonEmpty
        switchMap(employeeToSave => this.employeesService.createEmployee(employeeToSave)),
        tap(() => this.employeesService.reloadEmployees())
      ).subscribe();
  }
}
