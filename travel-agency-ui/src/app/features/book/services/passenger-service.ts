import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PassengerService {
  private passengersApi = 'http://localhost:6060/api/passengers'

  constructor(private http:HttpClient) {
  }

  savePassenger(bookingId:number,passengers:any[]){
    return this.http.post(this.passengersApi + "/booking/" + bookingId,passengers);
  }

}
