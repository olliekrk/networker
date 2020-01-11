import {Injectable} from "@angular/core";
import {ActivityRestService} from "../rest/activity-rest.service";
import {EmployeeId} from "../model/employee";
import {Observable} from "rxjs";
import {PresenceChartData} from "../model/presence";
import {share} from "rxjs/operators";

@Injectable()
export class ActivityService {

  constructor(private activityRest: ActivityRestService) {
  }

  getPresenceChartDataForEmployee(employeeId: EmployeeId): Observable<PresenceChartData> {
    return this.activityRest.getPresenceChartDataForEmployee(employeeId).pipe(share());
  }
}
