import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {SettingsPanelComponent} from "./components/settings-panel/settings-panel.component";
import {ExamplesComponent} from "./components/examples/examples.component";
import {EmployeesMainViewComponent} from "./components/manager-views/employees-main-view/employees-main-view.component";
import {MeetingsMainViewComponent} from "./components/manager-views/meetings-main-view/meetings-main-view.component";


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
    path: "settings",
    component: SettingsPanelComponent
  },
  {
    path: "examples",
    component: ExamplesComponent
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
