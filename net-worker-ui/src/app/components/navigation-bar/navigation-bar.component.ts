import {Component, ViewChild} from "@angular/core";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Observable} from "rxjs";
import {filter, map, shareReplay, withLatestFrom} from "rxjs/operators";
import {NavigationEnd, Router} from "@angular/router";
import {MatSidenav} from "@angular/material";
import {LabeledLink} from "../../model/utils";

@Component({
  selector: "app-navigation-bar",
  templateUrl: "./navigation-bar.component.html",
  styleUrls: ["./navigation-bar.component.scss"]
})
export class NavigationBarComponent {

  @ViewChild("drawer", {static: true}) drawer: MatSidenav;

  readonly navigationLinks: LabeledLink[] = [
    {
      path: "/dashboard",
      label: "Dashboard",
    },
    {
      path: "/presence",
      label: "Presence data",
    },
    {
      path: "/settings",
      label: "Settings",
    },
    {
      path: "/credentials",
      label: "Credentials",
    },
  ];

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private router: Router) {
    router.events.pipe(
      withLatestFrom(this.isHandset$),
      filter(([a, b]) => b && a instanceof NavigationEnd)
    ).subscribe(_ => this.drawer.close());
  }

}
