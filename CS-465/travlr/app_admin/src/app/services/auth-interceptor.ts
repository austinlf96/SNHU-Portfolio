import { inject } from '@angular/core';
import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { Authentication } from './authentication';

const API_URL = 'http://localhost:3000/api';

// Attach the stored JWT to API requests as a Bearer token.
// Limited to our own API so the token is never sent to third-party hosts.
// If the API rejects the token (expired or invalid), log the user out and send them to the login page.
export const authInterceptor: HttpInterceptorFn = (req, next) => {
    const authentication = inject(Authentication);
    const router = inject(Router);
    const isApi = req.url.startsWith(API_URL);
    const isAuthCall = req.url.endsWith('/login') || req.url.endsWith('/register');

    const token = authentication.getToken();
    if (token && isApi) {
        req = req.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
    }

    return next(req).pipe(
        catchError((err: unknown) => {
            // A 401/403 from login/register just means bad credentials, so leave those to the form
            if (isApi && !isAuthCall && err instanceof HttpErrorResponse && (err.status === 401 || err.status === 403)) {
                authentication.logout();
                router.navigate(['login']);
            }
            return throwError(() => err);
        })
    );
};
