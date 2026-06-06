import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Flight} from '../model/Flight'

@Injectable({
  providedIn: 'root',
})
export class BookingService {

    private apiUrl = 'http://localhost:6060/api/flights';

    constructor(private http:HttpClient) {}

    getFlight():Observable<any>{
      return this.http.get<any>(this.apiUrl);
    }

}





