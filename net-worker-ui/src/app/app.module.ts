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
import {MatDialogModule, MatFormFieldModule, MatInputModule, MatTabsModule, MatTooltipModule} from "@angular/material";
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
import {ReactiveFormsModule} from "@angular/forms";

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
    EmployeeEditDialogComponent,
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
  ],
  providers: [
    DashboardService,
    DashboardRestService,
    EmployeesService,
    EmployeesRestService,
  ],
  bootstrap: [AppComponent],
  entryComponents: [EmployeeEditDialogComponent]
})
export class AppModule {
}
