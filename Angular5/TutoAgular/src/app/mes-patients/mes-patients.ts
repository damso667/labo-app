import { Component, OnInit } from '@angular/core';
import { Medecin } from '../services/medecin';
import { NgIf, NgForOf } from '@angular/common';

@Component({
  selector: 'app-mes-patients',
  imports: [NgIf,NgForOf],
  templateUrl: './mes-patients.html',
  styleUrl: './mes-patients.css'
})
export class MesPatients implements OnInit {
 patients: any[] = [];
  message = '';

  constructor(private medecinService: Medecin) {}

  ngOnInit(): void {
    this.medecinService.getMesPatients().subscribe({
      next: (res: any) => this.patients = res,
      error: () => this.message = 'Erreur de chargement de vos patients.'
    });
  }
}
