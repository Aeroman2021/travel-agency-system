import {Injectable} from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';
import {TokenResponse} from '../../model/TokenResponse';
import {Observable, Observer} from 'rxjs';
import {Router} from '@angular/router';


@Injectable({
  providedIn: 'root',
})


export class Auth {

  private tokenUrl = 'http://localhost:6060/api/auth/login';
  constructor(private http: HttpClient,private observer:Observer<any>,private router:Router) {}

  login(username:string,password:string){
    return this.http.post<TokenResponse>(this.tokenUrl,{username,password})
  }

  logout(): void {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    this.router.navigate(['/login']);
  }

  refreshToken():Observable<any>{
    const refreshToken = localStorage.getItem('refresh_token');
    return this.http.post<any>('http://localhost:6060/api/auth/refresh',{
      refreshToken: refreshToken
    })
  }

  saveToken(response: any){
    localStorage.setItem('access_token',response.access_token);
    localStorage.setItem('refresh_token',response.refresh_token);
  }

  getCurrentUsername(): string{
    const accessToken = localStorage.getItem('access_token');
    if(!accessToken){
      return '';
    }

    const payload = JSON.parse(
      atob(accessToken.split('.')[1])
    );

    return payload.preferred_username;
  }
}


