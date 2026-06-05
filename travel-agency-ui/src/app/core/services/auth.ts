import {Injectable} from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})


export class Auth {

  private tokenUrl = 'http://localhost:6060/api/auth/login';
  constructor(private http: HttpClient) {}

  login(username:string,password:string){
    return this.http.post<TokenResponse>(this.tokenUrl,{username,password})
  }
}

export interface TokenResponse {
  access_token: string;
  refresh_token: string;
  expires_in: number;
}
