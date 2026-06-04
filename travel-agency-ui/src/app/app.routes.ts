import {Routes} from '@angular/router';

import {Dashboard} from './pages/dashboard/dashboard';
import {Passengers} from './pages/passengers/passengers';
import {Booking} from './pages/booking/booking';
import {Login} from './login/pages/login/login';
import {MainBar} from './layout/components/main-bar/main-bar';

export const routes: Routes = [

  {
    path: 'login',
    component: Login
  },

  {
    path:'',
    component:MainBar,
    children:[
      {path: 'dashboard', component: Dashboard},
      {path: 'passenger', component: Passengers},
      {path: 'booking', component: Booking},
      {path: '', redirectTo: 'dashboard',pathMatch: 'full'}
    ]
  }

];
