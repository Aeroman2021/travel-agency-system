import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BookingService} from '../book/services/booking-service';
import {BookingView} from '../../core/model/BookingView';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';

import { CommonModule } from '@angular/common';
import {MatFormField, MatLabel} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import {MatButton} from '@angular/material/button';
import {PassengerService} from '../book/services/passenger-service';

@Component({
  standalone:true,
  selector: 'app-passengers',
  imports: [CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatLabel,
    MatSelect,
    MatOption,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule, MatButton],
  templateUrl: './passengers.html',
  styleUrl: './passengers.scss',
})
export class Passengers {
  bookingId!: number;
  booking!:BookingView;
  passengerForm!: FormGroup;

  constructor(private route: ActivatedRoute,
              private bookingService:BookingService,
              private fb:FormBuilder,
              private passengerService:PassengerService) {
    this.passengerForm = this.fb.group({
      passengers: this.fb.array([])
    });
  }

  get passengers():FormArray{
    return this.passengerForm.get('passengers') as FormArray;
  }

  ngOnInit() {

    this.bookingId = Number(this.route.snapshot.paramMap.get('id'));

    this.bookingService.getBookingById(this.bookingId)
      .subscribe({
        next: booking=>{
          this.booking = booking;
          this.buildPassengerForm();
        }
      })

  }

  buildPassengerForm(){
    this.passengers.clear();

    for (let i = 0; i < this.booking.numberOfPassengers; i++) {
      this.passengers.push(this.createPassengerForm())
    }
  }

  createPassengerForm(): FormGroup {

    return this.fb.group({
      fullName:['',Validators.required],
      ncode:['',Validators.required,Validators.minLength(10),Validators.maxLength(10)],
      passportNumber:['',Validators.required],
      sex:['',Validators.required],
      dob: ['',Validators.required]
    });
  }


  protected submitPassengers() {
    if(this.passengerForm.invalid){
      this.passengerForm.markAllAsTouched();
      return;
    }

    const request  = this.passengerForm.value.passengers;

    this.passengerService
      .savePassenger(this.bookingId,request)
      .subscribe({
        next: response =>{
          console.log(response);
        },
        error:err => {
          console.error(err)
        }
      })

  }
}
