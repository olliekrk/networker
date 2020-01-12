import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {SettingsPanelComponent} from "./components/settings-panel/settings-panel.component";
import {CredentialsComponent} from "./components/credentials/credentials.component";
import {PresenceHoursPanelComponent} from "./components/presence-hours-panel/presence-hours-panel.component";
import {GoogleIntegrationPanelComponent} from "./components/google-integration/google-integration-panel/google-integration-panel.component";


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
    path: "googleIntegration",
    component: GoogleIntegrationPanelComponent
  },
  {
    path: "settings",
    component: SettingsPanelComponent
  },
  {
    path: "credentials",
    component: CredentialsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
