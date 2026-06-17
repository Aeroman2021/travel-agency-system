import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BookingPayload} from '../models/BookingPayload';


@Injectable({
  providedIn: 'root',
})
export class BookingService {

    private myBookingApi = 'http://localhost:6060/api/booking-view/my-booking'
    private bookingApiUrl = 'http://localhost:6060/api/bookings';
    private bookingViewApiUrl = 'http://localhost:6060/api/booking-view';

    constructor(private http:HttpClient ) {}

    getMyBookings():Observable<any>{
      return this.http.get<any>(this.myBookingApi);
    }

    createBooking(bookingPayLoad:BookingPayload):Observable<any>{
      return this.http.post(this.bookingApiUrl,bookingPayLoad)
    }

    getBookingById(id:Number){
      return this.http.get<any>(this.bookingViewApiUrl + '/' + id)
    }

}





