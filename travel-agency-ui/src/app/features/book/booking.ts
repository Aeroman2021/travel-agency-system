import {Component} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {BookingService} from './services/booking-service';
import {BookingView} from '../../core/model/BookingView';
import {Router} from '@angular/router';



@Component({
  selector: 'app-booking',
  imports: [MatTableModule, CommonModule],
  templateUrl: './booking.html',
  styleUrl: './booking.scss',
})
export class Booking {

  constructor(private bookingService: BookingService,private router:Router) {
  }


  displayedColumns:string[] = [
  'flightNumber',
  'cabinClass',
  'numberOfPassengers',
  'totalPrice',
  'bookingStatus'
  ]

  dataSource = new MatTableDataSource<BookingView>();

  ngOnInit(): void {


    this.bookingService.getMyBookings()
      .subscribe({
        next: (data) => {
          this.dataSource.data = data.content;
        },
        error: (err) => {
          console.error(err);
        }
      })
  }

  protected openPassengerPage(booking: BookingView) {
    this.router.navigate(['/passengers',booking.id]);
  }
}

