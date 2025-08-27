import { Component } from '@angular/core';
import { Auth } from '../services/auth';
import { NgIf } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [NgIf,RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
  role: 'ROLE_MEDECIN' | 'ROLE_TECHNITIEN' | 'ROLE_SECRETAIRE' | null = null;

  constructor(private auth: Auth, private router: Router) {
    this.role = this.auth.getRole?.() as 'ROLE_MEDECIN' | 'ROLE_TECHNITIEN' | 'ROLE_SECRETAIRE' | null ; // méthode que tu avais pour récupérer le rôle
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
