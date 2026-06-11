import { HttpEvent,HttpHandler,HttpInterceptor,HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class authInterceptor implements HttpInterceptor{

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.includes('/api/auth') ||
      req.url.includes('/api/flight-view')) {
      return next.handle(req);
    }

    const token = localStorage.getItem('access_token');

    if(token){
      const cloneRequest = req.clone({
        setHeaders:{
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(cloneRequest);
    }
    return next.handle(req)
  }

}
