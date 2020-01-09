import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {share} from "rxjs/operators";
import {HelloMessage} from "../model/dashboard";
import {DashboardRestService} from "../rest/dashboard-rest.service";

@Injectable()
export class DashboardService {

  constructor(private dashboardRestService: DashboardRestService) {
  }

  getHelloMessage(): Observable<HelloMessage> {
    return this.dashboardRestService.getHelloMessage().pipe(share());
  }
}
