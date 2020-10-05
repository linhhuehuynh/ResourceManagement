import { HttpHandler, HttpInterceptor, HttpRequest, HttpEvent } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private authService: AuthService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>>  {
        
        const authToken = this.authService.getToken();
        let newHeaders = req.headers;
        if (authToken) {
            // If we have a token, we append it to our new headers
            newHeaders = req.headers.set("Authorization", "Bearer " +authToken)
         }
        const authRequest = req.clone({
            headers: newHeaders
        });
        return next.handle(authRequest);
    }
}

