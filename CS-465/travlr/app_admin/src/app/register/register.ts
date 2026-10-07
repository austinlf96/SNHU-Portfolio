import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Authentication } from '../services/authentication';
import { User } from '../models/user';

@Component({
  imports: [CommonModule, FormsModule, RouterLink],
  selector: 'app-register',
  styleUrl: './register.css',
  templateUrl: './register.html',
})

export class Register {
  public formError: string = '';
  credentials = {
    name: '',
    email: '',
    password: '',
    confirmPassword: '',
  };

  constructor(
    private router: Router,
    private authentication: Authentication
  ) {}

  public onRegisterSubmit(): void {
    this.formError = '';

    if (!this.credentials.name || !this.credentials.email || !this.credentials.password) {
      this.formError = 'All fields are required.';
      return;
    }
    if (this.credentials.password !== this.credentials.confirmPassword) {
      this.formError = 'Passwords do not match.';
      return;
    }
    this.doRegister();
  }

  private doRegister(): void {
    const newUser = { email: this.credentials.email, name: this.credentials.name } as User;

    // The API logs the new user in by returning a JWT, so go straight to the trip listing
    this.authentication.register(newUser, this.credentials.password).subscribe({
      next: () => this.router.navigate(['']),
      error: (err) => {
        this.formError = err?.error?.message || 'Registration failed.';
      }
    });
  }
}
