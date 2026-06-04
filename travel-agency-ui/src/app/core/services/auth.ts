import {Injectable} from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})


export class Auth {

  private tokenUrl = 'http://127.0.0.1:8180/realms/travel-agency/protocol/openid-connect/token';
  constructor(private http: HttpClient) {}

  login(username:string,password:string){
    const body = new HttpParams()
      .set('client_id','travel-agency-client')
      .set('grant_type','password')
      .set('username',username)
      .set('password',password);

    return this.http.post(
      this.tokenUrl,
      body.toString(),
      {
        headers:{
          'Content-type':'application/x-www-form-urlencoded'
        }
      }
    )
  }
}
