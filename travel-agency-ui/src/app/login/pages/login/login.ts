import {Component} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {Auth} from '../../../core/services/auth';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatInputModule, MatFormFieldModule, MatButtonModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  username = '';
  password = '';

  constructor(
    private auth: Auth,
    private router: Router
  ) {
  }


  onLogin(){
    this.auth
      .login(this.username,this.password)
      .subscribe({
        next : (response)=>{
          localStorage.setItem('access_token', response.access_token);
          this.router.navigate(['/booking'])
        },
        error : (err)=>{
          console.error(err);
        }
      })
  }

}


