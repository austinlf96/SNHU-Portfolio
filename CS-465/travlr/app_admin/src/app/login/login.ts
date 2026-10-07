import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Authentication } from '../services/authentication';

@Component({
  imports: [CommonModule, FormsModule],
  selector: 'app-login',
  styleUrl: './login.css',
  templateUrl: './login.html',
})

export class Login implements OnInit {
  public formError: string = '';
  submitted = false;
  credentials = {
    email: '',
    password: '',
  };

  constructor(
    private router: Router,
    private authentication: Authentication
  ) {}

  ngOnInit() : void {}

  public onLoginSubmit(): void {
    this.submitted = true;
    this.formError = '';

    if (!this.credentials.email || !this.credentials.password) {
      this.formError = 'Email and password are required.';
      return;
    }
    this.doLogin();
  }

  private doLogin(): void {
    this.authentication.login(this.credentials.email, this.credentials.password).subscribe({
      next: () => this.router.navigate(['']),
      error: (err) => {
        this.formError = err?.error?.message || 'Login failed.';
      }
    });
  }
}
