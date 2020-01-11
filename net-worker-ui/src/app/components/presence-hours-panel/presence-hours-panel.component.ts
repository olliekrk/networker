import {Component, OnInit} from "@angular/core";
import {EmployeesService} from "../../services/employees.service";
import {Employee, EmployeeId, getReadableEmployeeName} from "../../model/employee";
import {Observable} from "rxjs";
import {DayOfWeek} from "../../model/date-time";
import {FormControl} from "@angular/forms";
import {ActivityService} from "../../services/activity.service";
import {PresenceChartData} from "../../model/presence";
import {filter, switchMap, tap} from "rxjs/operators";

@Component({
  selector: "app-presence-hours-panel",
  templateUrl: "./presence-hours-panel.component.html",
  styleUrls: ["./presence-hours-panel.component.scss"]
})
export class PresenceHoursPanelComponent implements OnInit {

  readonly days: DayOfWeek[] = Object.values(DayOfWeek);

  employees$: Observable<Employee[]>;
  chartData$: Observable<PresenceChartData>;

  employeeIdControl: FormControl = new FormControl();
  dayControl: FormControl = new FormControl(DayOfWeek.Monday);

  getReadableEmployeeName = getReadableEmployeeName;

  constructor(private employeesService: EmployeesService,
              private activityService: ActivityService) {
  }

  ngOnInit() {
    this.employees$ = this.employeesService.getEmployees().pipe(
      filter(employees => !!employees),
      tap(employees => this.employeeIdControl.setValue(employees[0].id))
    );
    this.chartData$ = this.employeeIdControl.valueChanges.pipe(switchMap(employeeId => this.onEmployeeChange(employeeId)));
  }

  private onEmployeeChange(employeeId: EmployeeId): Observable<PresenceChartData> {
    return this.activityService.getPresenceChartDataForEmployee(employeeId);
  }

}
