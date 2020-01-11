import {Component, OnInit} from "@angular/core";
import {MatDialog} from "@angular/material";
import {RoomEditDialogComponent} from "../room-edit-dialog/room-edit-dialog.component";
import {filter, switchMap, take, tap} from "rxjs/operators";
import {RoomService} from "../../../services/room.service";
import {EditorMode} from "../../../model/utils";
import {Room} from "../../../model/room";

@Component({
  selector: "app-rooms-main-view",
  templateUrl: "./rooms-main-view.component.html",
  styleUrls: ["./rooms-main-view.component.scss"]
})
export class RoomsMainViewComponent implements OnInit {

  employees: Room[] = [];

  constructor(private dialog: MatDialog,
              private employeesService: RoomService) {
  }

  ngOnInit() {
    this.reloadEmployeesList();
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
        tap(() => this.reloadEmployeesList())
      ).subscribe();
  }

  private reloadEmployeesList(): void {
    this.employeesService.getAllEmployees().pipe(take(1))
      .subscribe(employees => this.employees = employees);
  }
}
