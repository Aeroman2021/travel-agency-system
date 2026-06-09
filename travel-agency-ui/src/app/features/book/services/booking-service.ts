import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BookingPayload} from '../models/BookingPayload';


@Injectable({
  providedIn: 'root',
})
export class BookingService {

    private apiUrl = 'http://localhost:6060/api/flight-view';
    private bookingApiUrl = 'http://localhost:6060/api/bookings';

    constructor(private http:HttpClient ) {}

    getFlight():Observable<any>{
      return this.http.get<any>(this.apiUrl);
    }

    createBooking(bookingPayLoad:BookingPayload):Observable<any>{
      return this.http.post(this.bookingApiUrl,bookingPayLoad)
    }

}





