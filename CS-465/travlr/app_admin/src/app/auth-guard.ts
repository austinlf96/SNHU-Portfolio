import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { Authentication } from './services/authentication';

// Only lets logged-in users reach a route; everyone else is sent to the login page
export const authGuard: CanActivateFn = () => {
  const authentication = inject(Authentication);
  const router = inject(Router);
  return authentication.isLoggedIn() ? true : router.createUrlTree(['login']);
};
