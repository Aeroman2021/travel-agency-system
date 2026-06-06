import {Routes} from '@angular/router';

import {Dashboard} from './pages/dashboard/dashboard';
import {Passengers} from './pages/passengers/passengers';
import {Booking} from './pages/booking/booking';
import {Login} from './pages/login-page/login';
import {MainBar} from './layout/components/main-bar/main-bar';
import {authGuard} from './core/guard/auth-guard';
import {Register} from './pages/reg/register';

export const routes: Routes = [

  {path: 'login', component: Login},
  {path: 'register', component: Register},

  {
    path:'',
    component:MainBar,
    children:[
      {path: 'dashboard', component: Dashboard},
      {path: 'passenger', component: Passengers},
      {path: 'booking', component: Booking,canActivate:[authGuard]},
      {path: '', redirectTo: 'dashboard',pathMatch: 'full'}
    ]
  }

];
