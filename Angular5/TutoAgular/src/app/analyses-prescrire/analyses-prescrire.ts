import { NgForOf, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Medecin } from '../services/medecin';
import { TypeExamen } from '../services/type-examen';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-analyses-prescrire',
  imports: [FormsModule,NgForOf,NgIf],
  templateUrl: './analyses-prescrire.html',
  styleUrl: './analyses-prescrire.css'
})
export class AnalysesPrescrire implements OnInit {
  patients: any[] = [];
  examens: any[] = [];
  selectedPatient!: number;
  selectedExamen!: number;
  description: string = '';
  message = '';

  constructor(
    private medecinService: Medecin,
    private typeExamenService: TypeExamen
  ) {}

  ngOnInit(): void {
    this.medecinService.getMesPatients().subscribe(res => this.patients = res);
    this.typeExamenService.getAll().subscribe((res: any) => this.examens = res.data);
  }

  prescrire() {
    this.medecinService.prescrireAnalyse(this.selectedPatient, this.selectedExamen, this.description)
      .subscribe({
        next: () => this.message = 'Analyse prescrite ✅',
        error: () => this.message = 'Erreur lors de la prescription.'
      });
  }
}
