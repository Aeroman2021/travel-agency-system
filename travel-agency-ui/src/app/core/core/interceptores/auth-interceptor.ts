import { HttpEvent,HttpHandler,HttpInterceptor,HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,
  catchError,
  switchMap,
  throwError } from 'rxjs';
import {Auth} from '../../../core/services/authentication/auth';

@Injectable()
export class authInterceptor implements HttpInterceptor{

  constructor(private auth:Auth) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (
      req.url.includes('/api/auth/login') ||
      req.url.includes('/api/auth/refresh') ||
      req.url.includes('/api/flight-view')
    ) {
      return next.handle(req);
    }

    const token = localStorage.getItem('access_token');

    if(token){
      const cloneRequest = req.clone({
        setHeaders:{
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(cloneRequest).pipe(
        catchError(error=>{
          if(error.status == 401){
            return this.handle401Error(
              cloneRequest,next
            );
          }
          return throwError(
            () => error
          );
        })
      )
    }
    return next.handle(req)
  }

  private handle401Error(
    request: HttpRequest<any>,
    next: HttpHandler
  ) {

    return this.auth
      .refreshToken()
      .pipe(

        switchMap(response => {

          this.auth.saveToken(response);

          const newRequest = request.clone({
            setHeaders: {
              Authorization:
                `Bearer ${response.access_token}`
            }
          });

          return next.handle(newRequest);
        }),

        catchError(error => {

          this.auth.logout();
          return throwError(
            () => error
          );

        })
      );
  }

}
