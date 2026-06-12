import {Component} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {BookingService} from './services/booking-service';
import {BookingView} from '../../core/model/BookingView';


@Component({
  selector: 'app-booking',
  imports: [MatTableModule, CommonModule],
  templateUrl: './booking.html',
  styleUrl: './booking.scss',
})
export class Booking {

  constructor(private bookingService: BookingService) {
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
          console.log(data.content);
          this.dataSource.data = data.content;
          console.log(this.dataSource.data.length);
        },
        error: (err) => {
          console.error(err);
        }
      })
  }

}

