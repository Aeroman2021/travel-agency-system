import { Component } from '@angular/core';
import {RouterOutlet,RouterLink,RouterLinkActive} from '@angular/router';
import {Router} from '@angular/router';
import {Auth} from '../../../core/services/authentication/auth';

@Component({
  selector: 'app-main-bar',
  standalone: true,
  imports: [RouterOutlet,RouterLink,RouterLinkActive],
  templateUrl: './main-bar.html',
  styleUrl: './main-bar.scss',
})
export class MainBar {
  constructor(private router:Router,private auth:Auth) {}

  username = '';

  logout(): void {
    localStorage.removeItem('access_token');
    this.router.navigate(['/login']);
  }

  ngOnInit(): void{
    this.username = this.auth.getCurrentUsername();
  }



}
