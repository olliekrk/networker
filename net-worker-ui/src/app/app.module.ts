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
import {CredentialsComponent} from "./components/credentials/credentials.component";
import {
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatTabsModule,
  MatTooltipModule
} from "@angular/material";
import {NavigationBarComponent} from "./components/navigation-bar/navigation-bar.component";
import {LayoutModule} from "@angular/cdk/layout";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatListModule} from "@angular/material/list";
import {EmployeesMainViewComponent} from "./components/management/employees-main-view/employees-main-view.component";
import {MeetingsMainViewComponent} from "./components/management/meetings-main-view/meetings-main-view.component";
import {ConferenceRoomsMainViewComponent} from "./components/management/conference-rooms-main-view/conference-rooms-main-view.component";
import {EmployeeEditDialogComponent} from "./components/management/employee-edit-dialog/employee-edit-dialog.component";
import {EmployeesService} from "./services/employees.service";
import {EmployeesRestService} from "./rest/employees-rest.service";
import {RoomService} from "./services/room.service";
import {RoomsRestService} from "./rest/rooms-rest.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {EmployeeInfoBarComponent} from "./components/management/employee-info-bar/employee-info-bar.component";
import {RoomEditDialogComponent} from "./components/management/room-edit-dialog/room-edit-dialog.component";
import {RoomInfoBarComponent} from "./components/management/room-info-bar/room-info-bar.component";
import {PresenceHoursPanelComponent} from "./components/presence-hours-panel/presence-hours-panel.component";
import {ChartsModule} from "ng2-charts";
import {PresenceHoursChartComponent} from "./components/presence-hours-chart/presence-hours-chart.component";
import {ActivityService} from "./services/activity.service";
import {ActivityRestService} from "./rest/activity-rest.service";
import {MeetingEditDialogComponent } from "./components/management/meeting-edit-dialog/meeting-edit-dialog.component";
import {MeetingRestService} from "./rest/meeting-rest.service";
import {MeetingInfoBarComponent} from "./components/management/meeting-info-bar/meeting-info-bar.component";
import {MeetingService} from "./services/meeting.service";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SettingsPanelComponent,
    CredentialsComponent,
    NavigationBarComponent,
    EmployeesMainViewComponent,
    MeetingsMainViewComponent,
    ConferenceRoomsMainViewComponent,
    EmployeeEditDialogComponent,
    RoomEditDialogComponent,
    EmployeeInfoBarComponent,
    PresenceHoursPanelComponent,
    PresenceHoursChartComponent,
    RoomInfoBarComponent,
    MeetingEditDialogComponent,
    MeetingInfoBarComponent,
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
    MatTooltipModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    ChartsModule,
    MatSelectModule,
    FormsModule,
  ],
  providers: [
    ActivityService,
    ActivityRestService,
    DashboardService,
    DashboardRestService,
    EmployeesService,
    EmployeesRestService,
    RoomService,
    RoomsRestService,
    MeetingRestService,
    MeetingService
  ],
  bootstrap: [AppComponent],
  entryComponents: [EmployeeEditDialogComponent, RoomEditDialogComponent, MeetingEditDialogComponent]
})
export class AppModule {
}
