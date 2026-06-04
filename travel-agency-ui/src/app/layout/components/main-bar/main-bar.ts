import { Component } from '@angular/core';
import {RouterOutlet,RouterLink,RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-main-bar',
  standalone: true,
  imports: [RouterOutlet,RouterLink,RouterLinkActive],
  templateUrl: './main-bar.html',
  styleUrl: './main-bar.scss',
})
export class MainBar {}
