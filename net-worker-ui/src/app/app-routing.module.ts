import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {SettingsPanelComponent} from "./components/settings-panel/settings-panel.component";
import {CredentialsComponent} from "./components/credentials/credentials.component";
import {EmployeesMainViewComponent} from "./components/management/employees-main-view/employees-main-view.component";
import {MeetingsMainViewComponent} from "./components/management/meetings-main-view/meetings-main-view.component";
import {PresenceHoursPanelComponent} from "./components/presence-hours-panel/presence-hours-panel.component";


const routes: Routes = [
  {
    path: "",
    redirectTo: "/dashboard",
    pathMatch: "full"
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
  {
    path: "presence",
    component: PresenceHoursPanelComponent
  },
  {
    path: "settings",
    component: SettingsPanelComponent
  },
  {
    path: "credentials",
    component: CredentialsComponent
  },
  {
    path: "employees",
    component: EmployeesMainViewComponent
  },
  {
    path: "meetings",
    component: MeetingsMainViewComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
