import {Component, OnInit} from "@angular/core";
import {Observable} from "rxjs";
import {HelloMessage} from "../../model/dashboard";
import {DashboardService} from "../../services/dashboard.service";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.scss"]
})
export class DashboardComponent implements OnInit {

  private message$: Observable<HelloMessage>;

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit() {
    this.message$ = this.dashboardService.getHelloMessage();
  }

}
