import {DayOfWeek} from "./date-time";

export interface PresenceHourlyInfo {
  hour: number,
  percentage: number,
}

export interface PresenceDailyInfo {
  dayOfWeek: DayOfWeek,
  hourlyData: PresenceHourlyInfo[],
}

export interface PresenceChartData {
  dailyData: PresenceDailyInfo[]
}
