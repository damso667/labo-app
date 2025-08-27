import { NgForOf, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Medecin } from '../services/medecin';

@Component({
  selector: 'app-patients-list',
  imports: [NgForOf,NgIf],
  templateUrl: './patients-list.html',
  styleUrl: './patients-list.css'
})
export class PatientsList implements OnInit {

  patients: any[] = [];
  message: string = '';

  constructor(private medecinService: Medecin) {}

  ngOnInit(): void {
    this.chargerPatients();
  }

  chargerPatients() {
    this.medecinService.getPatients().subscribe({
      next: (res: any) => this.patients = res,
      error: () => this.message = 'Erreur de chargement des patients.'
    });
  }

  recuperer(id: number) {
    this.medecinService.recupererPatient(id).subscribe({
      next: () => {
        this.message = 'Patient récupéré ✅';
        this.chargerPatients();
      },
      error: () => this.message = 'Impossible de récupérer ce patient.'
    });
  }

}
