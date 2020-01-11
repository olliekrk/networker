import {Component, OnInit} from "@angular/core";
import {EmployeesService} from "../../services/employees.service";
import {Employee, EmployeeId, getReadableEmployeeName} from "../../model/employee";
import {Observable} from "rxjs";
import {DayOfWeek} from "../../model/date-time";
import {FormControl} from "@angular/forms";
import {ActivityService} from "../../services/activity.service";
import {PresenceChartData} from "../../model/presence";
import {filter, map, switchMap, tap} from "rxjs/operators";
import {ChartDataSets} from "chart.js";

@Component({
  selector: "app-presence-hours-panel",
  templateUrl: "./presence-hours-panel.component.html",
  styleUrls: ["./presence-hours-panel.component.scss"]
})
export class PresenceHoursPanelComponent implements OnInit {

  readonly days: DayOfWeek[] = Object.values(DayOfWeek);

  employees$: Observable<Employee[]>;
  chartData$: Observable<ChartDataSets[]>;

  employeeIdControl: FormControl = new FormControl();
  dayControl: FormControl = new FormControl(DayOfWeek.Monday);

  getReadableEmployeeName = getReadableEmployeeName;

  constructor(private employeesService: EmployeesService,
              private activityService: ActivityService) {
  }

  ngOnInit() {
    this.chartData$ = this.employeeIdControl.valueChanges.pipe(
      switchMap(employeeId => this.onEmployeeChange(employeeId)),
      map(data => this.computeDataSets(data))
    );
    this.employees$ = this.employeesService.getEmployees().pipe(
      filter(employees => !!employees.length),
      tap(employees => this.employeeIdControl.setValue(employees[0].id))
    );

    this.employeesService.reloadEmployees();
    this.dayControl.valueChanges.subscribe(() => this.dumbReload());
  }

  private onEmployeeChange(employeeId: EmployeeId): Observable<PresenceChartData> {
    return this.activityService.getPresenceChartDataForEmployee(employeeId);
  }

  private computeDataSets(presenceData: PresenceChartData): ChartDataSets[] {
    return presenceData.dailyData
      .filter(dailyData => dailyData.dayOfWeek === this.dayControl.value)
      .map(dailyData => ({
        data: dailyData.hourlyData.map(hourlyData => ({x: hourlyData.hour, y: hourlyData.percentage})),
        label: dailyData.dayOfWeek
      }));
  }

  private dumbReload(): void {
    this.employeeIdControl.setValue(this.employeeIdControl.value);
  }
}
