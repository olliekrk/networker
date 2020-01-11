import {Injectable} from "@angular/core";
import {Employee} from "../model/employee";
import {Observable} from "rxjs";
import {EmployeesRestService} from "../rest/employees-rest.service";
import {share} from "rxjs/operators";

@Injectable()
export class EmployeesService {

  constructor(private employeesRest: EmployeesRestService) {
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.employeesRest.createEmployee(employee).pipe(share());
  }
}
