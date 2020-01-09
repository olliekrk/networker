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
import {MatTabsModule} from "@angular/material";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SettingsPanelComponent,
    ExamplesComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule
  ],
  providers: [
    DashboardService,
    DashboardRestService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
