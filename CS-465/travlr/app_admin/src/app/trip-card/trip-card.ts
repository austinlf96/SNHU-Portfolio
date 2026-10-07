import { Component, OnInit, Input } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { CommonModule } from '@angular/common';
import { Trip } from '../models/trip';
import {Router} from '@angular/router';
import { Authentication } from '../services/authentication';

@Component({
  imports: [CurrencyPipe, CommonModule],
  standalone: true,
  selector: 'app-trip-card',
  styleUrl: './trip-card.css',
  templateUrl: './trip-card.html',
})

export class TripCard implements OnInit{
  @Input('trip') trip: any;

  constructor(private router: Router, private authentication: Authentication) {
    // Initialization logic can go here if needed
  }

  ngOnInit(): void {
    // Lifecycle hook for additional initialization if needed
  }

  public editTrip(trip: Trip): void {
    // Hand the selected trip's code to the edit page via localStorage
    localStorage.removeItem('tripCode');
    localStorage.setItem('tripCode', trip.code);
    this.router.navigate(['edit-trip']);
  }

  public isLoggedIn(): boolean {
    return this.authentication.isLoggedIn();
  }
}
