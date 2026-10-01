import { Component, OnInit, Input } from '@angular/core';
import { CurrencyPipe } from '@angular/common';
import { CommonModule } from '@angular/common';
import { Trip } from '../models/trip';
import {Router} from '@angular/router';

@Component({
  imports: [CurrencyPipe, CommonModule],
  standalone: true,
  selector: 'app-trip-card',
  styleUrl: './trip-card.css',
  templateUrl: './trip-card.html',
})

export class TripCard implements OnInit{
  @Input('trip') trip: any;

  constructor(private router: Router) {
    // Initialization logic can go here if needed
  }

  ngOnInit(): void {
    // Lifecycle hook for additional initialization if needed
  }

  public editTrip(trip: Trip): void {
    localStorage.removeItem('tripCode');
    localStorage.setItem('tripCode', trip.code);
    this.router.navigate(['edit-trip']);
  }
}
