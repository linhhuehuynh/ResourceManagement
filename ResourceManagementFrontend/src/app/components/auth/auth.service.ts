import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Subject} from "rxjs";

import {environment} from "../../../environments/environment";
import {AuthData} from "./auth-data.model";

const BACKEND_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isAuthenticated = false;
  private token: string;
  private tokenTimer: any;
  private userId: string;
  private authStatusListener = new Subject<boolean>();
   message: string;

  constructor(private http: HttpClient, private router: Router) { }

  getToken() {
    return this.token;
  }

  getIsAuth() {
    return this.isAuthenticated;
  }

  getUserId(){
    return this.userId;
  }

  getAuthStatusListener() {
    return this.authStatusListener.asObservable();
  }

  createUser(username: string, password: string) {
    const authData: AuthData ={username, password, user: {} };
    return this.http.post(BACKEND_URL + "/signup", authData);
  }

  login(username: string, password: string) {
    const authData: AuthData= {username, password, user: {}};
    this.http
      .post<{jwt: string; expiresIn: string, id: string}>(
        BACKEND_URL + "/login",
        authData
    )
      .subscribe(
        res => {
          const token = res.jwt;
          this.token = token;
          if (token) {
            //change expiresIn to String or number
            const expiresInDuration = Date.parse(res.expiresIn);
            this.setAuthTimer(expiresInDuration);
            this.isAuthenticated = true;
            this.userId = res.id;
            this.authStatusListener.next(true);
            const now = new Date();
            const expirationDate = new Date(
              now.getTime() + expiresInDuration * 1000
            );
            this.saveAuthData(this.token, expirationDate, this.userId);
            this.router.navigate(["/project"]);
          }
          return res;
        },
        error => {
          this.message = error.error;
          console.log(error)
          this.authStatusListener.next(false);
        }
      );
  }

  autoAuthUser() {
    const authInformation = this.getAuthData();
    if (!authInformation) {
      return;
    }
    const now = new Date();
    const expiresIn = authInformation.expirationDate.getTime() - now.getTime();
    if (expiresIn > 0) {
      this.token = authInformation.token;
      this.isAuthenticated = true;
      this.userId = authInformation.userId;
      this.setAuthTimer(expiresIn / 1000);
      this.authStatusListener.next(true);
    }
  }

  logout() {
    this.token=null;
    this.isAuthenticated=false;
    this.authStatusListener.next(false);
    this.userId=null;
    clearTimeout(this.tokenTimer);
    this.clearAuthData();
    this.router.navigate(["/"]);
  }

  private setAuthTimer(duration: number){
    console.log("Setting timer: " + duration);
    this.tokenTimer = setTimeout(()=> {
      this.logout();
    }, duration *1000);
  }

  private saveAuthData(token:string, expirationDate: Date, userId: string) {
    localStorage.setItem("token", token);
    localStorage.setItem("expiration", expirationDate.toISOString());
    localStorage.setItem("id", userId);
    console.log(localStorage);
  }

  private clearAuthData() {
    localStorage.removeItem("token");
    localStorage.removeItem("expiration");
    localStorage.removeItem("id");
  }

  private getAuthData() {
    const token = localStorage.getItem("token");
    const expirationDate = localStorage.getItem("expiration");
    const userId = localStorage.getItem("id");
    if(!token || !expirationDate) {return;}
    return {
      token,
      expirationDate: new Date(expirationDate),
      userId
    };
  }
}
