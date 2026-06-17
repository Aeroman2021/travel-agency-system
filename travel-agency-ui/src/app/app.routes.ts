import {Routes} from '@angular/router';
import {MainBar} from './layout/components/main-bar/main-bar';
import {authGuard} from './core/guard/auth-guard';
import {Login} from './core/login-page/login';
import {Register} from './core/reg/register';
import {Dashboard} from './features/dashboard/dashboard';
import {Passengers} from './features/passengers/passengers';
import {FlightList} from './features/flight-list/flight-list';
import {Booking} from './features/book/booking';


export const routes: Routes = [

  {path: 'login', component: Login},
  {path: 'register', component: Register},

  {
    path:'',
    component:MainBar,
    children:[
      {path: 'flights',component:FlightList},
      {path: 'dashboard', component: Dashboard},
      {path: 'passengers/:id', component: Passengers},
      {path: 'booking', component: Booking,canActivate:[authGuard]},
      {path: '', redirectTo: 'dashboard',pathMatch: 'full'}
    ]
  }
];
