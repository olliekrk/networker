export interface Meeting {
  id?: number;

  employeeMeetingLeaderID?: number;

  employeesParticipantsID?: number[];

  roomID?: number;

  subject?: string;

  startTimestamp?: number;

  endTimestamp?: number;
}


