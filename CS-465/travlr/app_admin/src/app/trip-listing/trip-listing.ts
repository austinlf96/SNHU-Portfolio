//import { CurrencyPipe } from '@angular/common';
// import { JsonPipe } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { trips } from '../data/trips';
import { CommonModule } from '@angular/common';
import { TripCard } from '../trip-card/trip-card';
import { Trip } from '../models/trip';
import { TripData } from '../services/trip-data';
import { Router } from '@angular/router';

@Component({
  imports: [CommonModule, TripCard],
  standalone: true,
  selector: 'app-trip-listing',
  styleUrl: './trip-listing.css',
  templateUrl: './trip-listing.html',
  providers: [TripData]
})

export class TripListing implements OnInit{
  trips = signal<Trip[]>([]);
  message: string = '';

  constructor(private tripData: TripData, private router: Router) {
    console.log('TripListing component initialized');
  }

public addTrip(): void {
  this.router.navigate(['add-trip']);
}

private getStuff() {
  this.tripData.getTrips().subscribe({
      next: (value: any) => {
        this.trips.set(value);
        if (value.length > 0) {
          this.message = 'There are ' + value.length + ' trips available';
        } else {
          this.message = 'No trips were retrieved from the server';
        }
        console.log(this.message);
      },
      error: (error: any) => {
        console.error('Error:', error);
        this.message = 'Error fetching trips';
      }
    })
}
  ngOnInit(): void {
    console.log('ngOnInit called in TripListing component');
    this.getStuff();
  }
}
