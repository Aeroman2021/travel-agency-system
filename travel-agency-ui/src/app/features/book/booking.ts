import {Component} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {CommonModule} from '@angular/common';
import {Flight} from '../../core/model/Flight';
import {BookingService} from './services/booking-service';
import {MatDialog} from '@angular/material/dialog';
import {BookingDialog} from '../book/dialog/booking-dialog/booking-dialog';
import {Router} from '@angular/router';
import {FormBuilder, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-booking',
  imports: [MatTableModule, CommonModule],
  templateUrl: './booking.html',
  styleUrl: './booking.scss',
})
export class Booking {

  constructor(private bookingService: BookingService,
              private dialog: MatDialog,
              private router: Router,
              private fb: FormBuilder) {
  }

  displayedColumns: string[] = [
    'flightNumber',
    'airlineName',
    'originAirportDisplay',
    'destAirportDisplay',
    'departureTime',
    'arrivalTime',
    'startingPrice',
    'totalAvailableSeats',
    'availableCabins',
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


openBookingDialog(flight: any) {

  const dialogRef = this.dialog.open(
    BookingDialog,
    {
      width: '500px',
      data: flight
    }
  );

  dialogRef.afterClosed().subscribe(result => {

    if (!result) {
      return;
    }

    console.log(result);

    const request = {
      flightId: flight.id,
      cabinClass: result.cabinClass,
      numberOfPassengers: Number(result.numberOfPassengers)
    }

    this.bookingService.createBooking(request)
      .subscribe({
        next: (booking) => {
          this.router.navigate(['/passengers'],{
            state:{
              bookingData:booking
            }
          });
        },
        error: err => {
          console.error('Booking failed', err)
        }
      });

  });
}

}

