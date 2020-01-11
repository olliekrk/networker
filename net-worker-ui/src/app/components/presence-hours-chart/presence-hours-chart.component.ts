import {Component, Input} from "@angular/core";
import {ChartDataSets, ChartOptions, ChartType} from "chart.js";
import {Label} from "ng2-charts";
import {hoursLabels} from "../../model/date-time";

@Component({
  selector: "app-presence-hours-chart",
  templateUrl: "./presence-hours-chart.component.html",
  styleUrls: ["./presence-hours-chart.component.scss"]
})
export class PresenceHoursChartComponent {

  readonly barChartOptions: ChartOptions = {
    responsive: true,
  };
  readonly barChartType: ChartType = "bar";
  readonly barChartLegend = true;
  readonly barChartPlugins = [];

  @Input() barChartData: ChartDataSets[];
  barChartLabels: Label[] = hoursLabels;

  public chartClicked({event, active}: { event?: MouseEvent, active?: {}[] }): void {
  }

  public chartHovered({event, active}: { event: MouseEvent, active: {}[] }): void {
  }

}
