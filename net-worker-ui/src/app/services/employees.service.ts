import {Injectable} from "@angular/core";
import {Employee, EmployeeId} from "../model/employee";
import {BehaviorSubject, Observable} from "rxjs";
import {EmployeesRestService} from "../rest/employees-rest.service";
import {share, take} from "rxjs/operators";

@Injectable()
export class EmployeesService {

  private employees$: BehaviorSubject<Employee[]> = new BehaviorSubject<Employee[]>([]);

  constructor(private employeesRest: EmployeesRestService) {
    this.reloadEmployees();
  }

  reloadEmployees(): void {
    this.getAllEmployees().pipe(take(1)).subscribe(employees => this.employees$.next(employees));
  }

  getEmployees(): Observable<Employee[]> {
    return this.employees$.asObservable().pipe(share());
  }

  private getAllEmployees(): Observable<Employee[]> {
    return this.employeesRest.getAllEmployees().pipe(share());
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.employeesRest.createEmployee(employee).pipe(share());
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.employeesRest.updateEmployee(employee).pipe(share());
  }

  deleteEmployee(id: EmployeeId): Observable<void> {
    return this.employeesRest.deleteEmployee(id).pipe(share());
  }
}
