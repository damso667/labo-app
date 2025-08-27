import { Component, OnInit } from '@angular/core';
import { Auth } from '../services/auth';
import { Router } from '@angular/router';
import { NgIf, NgForOf, CommonModule } from '@angular/common';

interface QuickLink{
  label:string;
  route:string;
  desc:string;
  emoji:string;
}

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home  {
  role: 'ROLE_MEDECIN' | 'ROLE_TECHNITIEN' | 'ROLE_SECRETAIRE' | null = null;
  constructor(private auth: Auth, private router: Router) {
    this.role = this.auth.getRole?.() as 'ROLE_MEDECIN' | 'ROLE_TECHNITIEN' | 'ROLE_SECRETAIRE' | null ;
  }

  agir() {
    // Route "d’entrée" dans l’espace métier selon le rôle
    if (this.role === 'ROLE_MEDECIN') this.router.navigate(['/dashbord']);
    else if (this.role === 'ROLE_TECHNITIEN') this.router.navigate(['/dashbord']);
    else if (this.role === 'ROLE_SECRETAIRE') this.router.navigate(['/dashbord']);


    else this.router.navigate(['/login']);
  }
}
