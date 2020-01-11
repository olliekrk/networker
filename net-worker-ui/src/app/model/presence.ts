import {DayOfWeek} from "./date-time";

export interface PresenceHourlyInfo {
  hour: number,
  percentage: number,
}

export interface PresenceDailyInfo {
  hourlyData: PresenceHourlyInfo[],
  dayOfWeek: DayOfWeek,
}

export interface PresenceChartData {
  dailyData: PresenceDailyInfo[]
}
