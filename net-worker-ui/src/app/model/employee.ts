export type EmployeeId = number;

export interface Employee {
  id?: EmployeeId;

  firstName?: string;

  lastName?: string;

  email?: string;

  phone?: string;
}

export function getReadableEmployeeName(employee: Employee): string {
  const firstNamePart = employee.firstName ? employee.firstName + " " : "";
  const lastNamePart = employee.lastName ? employee.lastName : "";
  return firstNamePart + lastNamePart;
}
