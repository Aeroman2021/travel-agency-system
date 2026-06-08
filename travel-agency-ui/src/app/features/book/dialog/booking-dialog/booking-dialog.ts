import { Component,Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-booking-dialog',
  imports: [FormsModule,MatButtonModule,MatDialogModule,MatFormFieldModule,MatInputModule,MatSelectModule],
  templateUrl: './booking-dialog.html',
  styleUrl: './booking-dialog.scss',
})
export class BookingDialog {
  passengerCount=1;
  cabinClass='ECONOMY';

  constructor(@Inject(MAT_DIALOG_DATA) public flight:any,private dialogRef :MatDialogRef<BookingDialog>){
  }

  continueBooking(){
    this.dialogRef.close({
      flightId:this.flight.id,
      passengerCount:this.passengerCount,
      cabinClass:this.cabinClass
    })
  }

  close(){
    this.dialogRef.close();
  }




}
