import { Injectable, inject, signal } from '@angular/core';
import { BROWSER_STORAGE } from '../storage/storage';
import { User } from '../models/user';
import { AuthResponse } from '../models/auth-response';
import { Observable, tap } from 'rxjs';
import { TripData } from './trip-data';

@Injectable({ providedIn: 'root' })
export class Authentication {
    private storage = inject(BROWSER_STORAGE);
    private tripData = inject(TripData);

    authResp: AuthResponse = new AuthResponse();

    // Bumped whenever the token changes so zoneless templates re-evaluate isLoggedIn()
    private tokenVersion = signal(0);

    public getToken(): string {
        let out: any;
        out = this.storage.getItem('travlr-token');

        if (!out) {
            out = '';
        }

        return out;
    }

    public saveToken(token: string): void {
        this.storage.setItem('travlr-token', token);
        this.tokenVersion.update(v => v + 1);
    }

    public logout(): void {
        this.storage.removeItem('travlr-token');
        this.tokenVersion.update(v => v + 1);
    }

    public isLoggedIn(): boolean {
        this.tokenVersion(); // track for change detection
        const token = this.getToken();
        if (!token) {
            return false;
        }
        try {
            // Decode the JWT payload and check the expiry (exp is in seconds).
            // This is only a UI convenience; the API verifies the signature itself.
            const payload = JSON.parse(atob(token.split('.')[1]));
            return payload.exp > Date.now() / 1000;
        } catch {
            // Malformed token in storage: treat as logged out instead of breaking the template
            return false;
        }
    }

    public getCurrentUser(): User {
        if (this.isLoggedIn()) {
            const token : string = this.getToken();
            const { email, username } = JSON.parse(atob(token.split('.')[1]));
            return { email, name: username } as User;
        }
        throw new Error("User not logged in");
    }

    public login(email: string, password: string): Observable<AuthResponse> {
        return this.tripData.login(email, password).pipe(
            tap(res => {
                this.authResp = res;
                this.saveToken(res.token);
            })
        );
    }

    public register(user: User, password: string): void {
        this.tripData.register(user, password).subscribe({
            next: (res: any) => {
                console.log(res);
                this.authResp = res;
                this.saveToken(this.authResp.token);
            },
            error: (err) => {
                console.error('Registration failed', err);
            }
        });
    }
}