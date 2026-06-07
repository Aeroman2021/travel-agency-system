import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import {RegisterService} from '../../core/services/register/register-service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatCardModule],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {

  form = {
    username:'',
    password:'',
    email:'',
    firstName:'',
    lastName:'',
  }

  constructor(private regservice : RegisterService,private router:Router) {
  }

  onRegister(){
    this.regservice.register(this.form)
      .subscribe({
        next: () =>{
          console.log('User Created');
          this.router.navigate(['/login'])
        },
        error: (err)=>{
          console.error(err);
        }
      });
  }


}
