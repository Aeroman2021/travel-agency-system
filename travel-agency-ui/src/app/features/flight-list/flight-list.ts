import {Component} from '@angular/core';
import {Flight} from '../../core/model/Flight';
import {FlightService} from '../../core/services/flight-service';
import {Router} from '@angular/router';
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell, MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef, MatTable,
  MatTableDataSource,
  MatTableModule
} from '@angular/material/table';
import {CommonModule, DatePipe} from '@angular/common';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-flight-list',
  imports: [CommonModule, MatTableModule],
  templateUrl: './flight-list.html',
  styleUrl: './flight-list.scss',
})
export class FlightList {

  constructor(private flightService: FlightService,
              private router: Router) {
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

  ngOnInit() {
    this.flightService.getFlight()
      .subscribe({
        next: (data) => {
          this.dataSource.data = data.content;
        },
        error: err => {
          console.error(err);
        }
      })
  }

  protected selectFlight(flight: Flight) {
    const token = localStorage.getItem('access_token');

    if (!token) {
      this.router.navigate(['/login'], {
        queryParams: {
          redirect: `/api/flight-view/${flight.id}`
        }
      });
      return;
    }

    this.router.navigate(['/booking'], {
      state: {flight}
    });

  }
}
