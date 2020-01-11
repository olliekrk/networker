import {Injectable} from "@angular/core";
import {Employee, EmployeeId} from "../model/employee";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable()
export class EmployeesRestService {

  baseUrl: string = environment.apiUrl + "/employee";

  constructor(private httpClient: HttpClient) {
  }

  getAllEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(this.baseUrl + "/all");
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.post<Employee>(this.baseUrl, employee);
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.post<Employee>(this.baseUrl, employee);
  }

  deleteEmployee(id: EmployeeId): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}/${id}`);
  }
}
