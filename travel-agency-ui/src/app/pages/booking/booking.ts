import { Component } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-booking',
  imports: [MatTableModule,MatInputModule,MatFormFieldModule,FormsModule],
  templateUrl: './booking.html',
  styleUrl: './booking.scss',
})
export class Booking {
  searchText ='';

  displayedColumn :string[] = [
    'id','passenger','destination','status'
  ];

  dataSource =[
    {
      id:1,
      passenger: 'Mohsen Malakouti',
      destination: 'NewYork',
      status: 'PAID'
    },{
      id:2,
      passenger: 'Ehteram Darvishi',
      destination: 'NewYork',
      status: 'PAID'
    }
  ]
}
