import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { TripData } from '../services/trip-data';
import { Trip } from '../models/trip';

@Component({
  selector: 'app-edit-trip',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit-trip.html',
  styleUrl: './edit-trip.css'
})

export class EditTrip implements OnInit{
  public editForm!: FormGroup;
  trip!: Trip;
  submitted = false;
  message: string = '';

  constructor(private formBuilder: FormBuilder, private router: Router, private tripData: TripData) {}

  ngOnInit(): void {
    // tripCode is set by TripCard.editTrip(); without it there is nothing to edit
    const tripCode = localStorage.getItem('tripCode');
    if (!tripCode) {
      this.message = 'No trip code found in local storage.';
      this.router.navigate(['']);
      return;
    }

    console.log('EditTripComponent::ngOnInit');
    console.log('Trip code from local storage:', tripCode);

    this.editForm = this.formBuilder.group({
      code: ['', Validators.required],
      name: ['', Validators.required],
      length: ['', Validators.required],
      start: ['', Validators.required],
      resort: ['', Validators.required],
      perPerson: ['', Validators.required],
      image: ['', Validators.required],
      description: ['', Validators.required],
    });

    this.tripData.getTripByCode(tripCode).subscribe({
      // GET /api/trips/:tripCode returns an array, so take the first match
      next: (data: any) => {
        this.trip = Array.isArray(data) ? data[0] : data;
        this.editForm.patchValue({
          ...this.trip,
          // <input type="date"> needs yyyy-MM-dd, not a full ISO timestamp
          start: this.trip.start ? String(this.trip.start).slice(0, 10) : ''
        });
      },
      error: (error: any) => {
        console.error('Error fetching trip data:', error);
        this.message = 'Error fetching trip data. Please try again later.';
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.editForm.valid) {
      this.tripData.updateTrip(this.editForm.value).subscribe({
        next: (data: Trip) => {
          console.log('Trip updated successfully:', data);
          this.router.navigate(['']);
        },
        error: (error: any) => {
          console.error('Error updating trip:', error);
          this.message = 'Error updating trip. Please try again later.';
        }
      });
    }
  }

  get f() {
    return this.editForm.controls;
  }
}
