import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class FlightService {

  private flightViewUrl = 'http://localhost:6060/api/flight-view';

  constructor(private http:HttpClient) {
  }

  getFlight():Observable<any>{
    return this.http.get<any>(this.flightViewUrl)
  }


}
