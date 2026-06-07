import {Component} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {Flight} from '../../core/model/Flight';
import {BookingService} from './services/booking-service';


@Component({
  selector: 'app-booking',
  imports: [MatTableModule, CommonModule],
  templateUrl: './booking.html',
  styleUrl: './booking.scss',
})
export class Booking {

  constructor(private bookingService: BookingService) {
  }

  displayedColumns: string[] = [
    'flightNumber',
    'airlineName',
    'originAirportDisplay',
    'destAirportDisplay',
    'departureTime',
    'arrivalTime',
    'price',
    'availableSeats',
    'status'
  ];

  dataSource = new MatTableDataSource<Flight>();

  ngOnInit(): void {
    this.bookingService.getFlight()
      .subscribe({
        next: (data) => {
          this.dataSource.data = data.content;
        },
        error: (err) => {
          console.error(err);
        }
      })
  }

}

