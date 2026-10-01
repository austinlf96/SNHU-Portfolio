import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { TripData } from '../services/trip-data';

@Component({
  imports: [CommonModule, ReactiveFormsModule],
  standalone: true,
  selector: 'app-add-trip',
  styleUrl: './add-trip.css',
  templateUrl: './add-trip.html',
})

export class AddTrip implements OnInit {
  addForm!: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private router: Router, private tripData: TripData) {}

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      code: ['', Validators.required], 
      name: ['', Validators.required],
      length: ['', Validators.required],
      start: ['', Validators.required],
      resort: ['', Validators.required],
      perPerson: ['', Validators.required],
      image: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.addForm.valid) {
      this.tripData.addTrip(this.addForm.value).subscribe({
        next: (data: any) => {
          console.log('Trip added successfully:', data);
          this.router.navigate(['']);
        },
        error: (error: any) => {
          console.error('Error adding trip:', error);
        }
      });
    }
  }

  get f() {
    return this.addForm.controls;
  }
}
