import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Trip } from '../models/trip';
import { User } from '../models/user';
import { AuthResponse } from '../models/auth-response';

@Injectable({
    providedIn: 'root'
})
export class TripData {
    private http = inject(HttpClient);
    baseUrl = 'http://localhost:3000/api/trips'; // Adjust the URL to match your backend API endpoint
    
    getTrips(): Observable<Trip[]> {
        return this.http.get<Trip[]>(this.baseUrl);
    }

    addTrip(trip: Trip): Observable<Trip> {
        return this.http.post<Trip>(this.baseUrl, trip);
    }

    getTripByCode(tripCode: string): Observable<Trip> {
        return this.http.get<Trip>(`${this.baseUrl}/${tripCode}`);
    }

    updateTrip(formData: Trip): Observable<Trip> {
        return this.http.put<Trip>(`${this.baseUrl}/${formData.code}`, formData);
    }

    // Log in and store the JWT; the auth interceptor sends it on later API calls
    login(email: string, password: string): Observable<AuthResponse> {
        return this.handleAuthAPICall(email, password, 'login');
    }

    register(user: User, password: string): Observable<AuthResponse> {
        let formData = {
            username: user.name,
            email: user.email,
            password: password
        };
        return this.handleAuthAPICall(user.email, password, 'register', formData);
    }

    private handleAuthAPICall(email: string, password: string, endpoint: string, formData?: any): Observable<AuthResponse> {
        const url = `http://localhost:3000/api/${endpoint}`;
        const body = formData || { email, password };

        return this.http.post<AuthResponse>(url, body);
    }
}

