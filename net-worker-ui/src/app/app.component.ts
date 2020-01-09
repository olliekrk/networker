import {Component} from "@angular/core";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent {

  readonly dashboardPath = "/dashboard";
  readonly settingsPath = "/settings";
  title = "net-worker-ui";


}
