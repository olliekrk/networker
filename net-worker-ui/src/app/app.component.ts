import {Component} from "@angular/core";

export interface LabeledLink {
  path: string,
  label: string,
}

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class AppComponent {

  readonly tabLinks: LabeledLink[] = [
    {
      path: "/dashboard",
      label: "Dashboard",
    },
    {
      path: "/settings",
      label: "Settings",
    }, {
      path: "/examples",
      label: "Examples",
    },
  ];

}
