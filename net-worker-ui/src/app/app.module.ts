import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HttpClientModule} from "@angular/common/http";
import {SettingsPanelComponent} from "./components/settings-panel/settings-panel.component";
import {DashboardService} from "./services/dashboard.service";
import {DashboardRestService} from "./rest/dashboard-rest.service";
import {ExamplesComponent} from "./components/examples/examples.component";
import {MatTabsModule, MatTooltipModule} from "@angular/material";
import {NavigationBarComponent} from "./components/navigation-bar/navigation-bar.component";
import {LayoutModule} from "@angular/cdk/layout";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {EmployeesMainViewComponent} from "./components/manager-views/employees-main-view/employees-main-view.component";
import {MeetingsMainViewComponent} from "./components/manager-views/meetings-main-view/meetings-main-view.component";
import {ConferenceRoomsMainViewComponent} from "./components/manager-views/conference-rooms-main-view/conference-rooms-main-view.component";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SettingsPanelComponent,
    ExamplesComponent,
    NavigationBarComponent,
    EmployeesMainViewComponent,
    MeetingsMainViewComponent,
    ConferenceRoomsMainViewComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTooltipModule
  ],
  providers: [
    DashboardService,
    DashboardRestService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
