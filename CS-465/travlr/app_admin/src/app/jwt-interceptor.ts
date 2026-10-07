// NOTE: currently unused. The app registers the functional interceptor in
// services/auth-interceptor.ts via provideHttpClient(withInterceptors(...)).
// Keep only one of the two approaches to avoid confusion.
import { Injectable, Provider } from '@angular/core';
import {HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpInterceptorFn, HTTP_INTERCEPTORS} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Authentication } from './services/authentication';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authentication: Authentication) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    var isAuthAPI: boolean;
    if (request.url.startsWith('login') || request.url.startsWith('register')) {
      isAuthAPI = true;
    } else {
      isAuthAPI = false;
    }

    if (this.authentication.isLoggedIn() && !isAuthAPI) {
      let token = this.authentication.getToken();
      const authReq = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(authReq);
    }
    
    return next.handle(request);
  }
}

export const authInterceptorProvider: Provider = {
  provide: HTTP_INTERCEPTORS,
  useClass: JwtInterceptor,
  multi: true
};
  