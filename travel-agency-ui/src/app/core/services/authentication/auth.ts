import {Injectable} from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';
import {TokenResponse} from '../../model/TokenResponse';

@Injectable({
  providedIn: 'root',
})


export class Auth {

  private tokenUrl = 'http://localhost:6060/api/auth/login';
  constructor(private http: HttpClient) {}

  login(username:string,password:string){
    return this.http.post<TokenResponse>(this.tokenUrl,{username,password})
  }

  getCurrentUsername(): string{
    const token = localStorage.getItem('access_token');
    if(!token){
      return '';
    }

    const payload = JSON.parse(
      atob(token.split('.')[1])
    );

    return payload.preferred_username;
  }
}


