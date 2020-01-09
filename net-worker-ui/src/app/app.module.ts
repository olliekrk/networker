import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppRoutingModule} from "./app-routing.module";
import {AppComponent} from "./app.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {HttpClientModule} from "@angular/common/http";
import {SettingsPanelComponent} from "./components/settings-panel/settings-panel.component";
import {DashboardService} from "./services/dashboard.service";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SettingsPanelComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  providers: [
    DashboardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
