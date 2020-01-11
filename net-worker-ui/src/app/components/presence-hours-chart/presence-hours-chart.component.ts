import {Component, OnInit} from "@angular/core";
import {ChartDataSets, ChartOptions, ChartType} from "chart.js";
import {Label} from "ng2-charts";

@Component({
  selector: "app-presence-hours-chart",
  templateUrl: "./presence-hours-chart.component.html",
  styleUrls: ["./presence-hours-chart.component.scss"]
})
export class PresenceHoursChartComponent implements OnInit {

  readonly barChartOptions: ChartOptions = {
    responsive: true,
  };
  readonly barChartLabels: Label[] = ["2006", "2007", "2008", "2009", "2010", "2011", "2012"];
  readonly barChartType: ChartType = "bar";
  readonly barChartLegend = true;
  readonly barChartPlugins = [];

  public barChartData: ChartDataSets[] = [
    {data: [65, 59, 80, 81, 56, 55, 40], label: "Series A"},
    {data: [28, 48, 40, 19, 86, 27, 90], label: "Series B"}
  ];

  constructor() {
  }

  ngOnInit() {
  }

  public chartClicked({event, active}: { event?: MouseEvent, active?: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({event, active}: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

}
