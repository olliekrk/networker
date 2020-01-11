import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {EmployeeId} from "../model/employee";
import {PresenceChartData} from "../model/presence";
import {Observable} from "rxjs";

@Injectable()
export class ActivityRestService {

  baseUrl: string = environment.apiUrl + "/activity";

  constructor(private httpClient: HttpClient) {
  }

  getPresenceChartDataForEmployee(employeeId: EmployeeId): Observable<PresenceChartData> {
    return this.httpClient.get<PresenceChartData>(`${this.baseUrl}/presence/${employeeId}`);
  }
}
