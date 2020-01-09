import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {HelloMessage} from "../model/dashboard";

@Injectable()
export class DashboardRestService {

  baseUrl: string = environment.apiUrl + "/dashboard";

  constructor(private httpClient: HttpClient) {
  }

  getHelloMessage(): Observable<HelloMessage> {
    return this.httpClient.get<HelloMessage>(this.baseUrl + "/hello");
  }
}
