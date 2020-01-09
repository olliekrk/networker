import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {SettingsPanelComponent} from "./components/settings-panel/settings-panel.component";


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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
