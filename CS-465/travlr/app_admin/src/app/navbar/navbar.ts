import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { Authentication } from '../services/authentication';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterLink, RouterModule],
  selector: 'app-navbar',
  standalone: true,
  styleUrl: './navbar.css',
  templateUrl: './navbar.html',
})
export class Navbar implements OnInit {

  constructor(private authentication: Authentication) {}

  ngOnInit() {}

  public isLoggedIn(): boolean {
    return this.authentication.isLoggedIn();
  }

  public onLogout(): void {
    this.authentication.logout();
  }
}