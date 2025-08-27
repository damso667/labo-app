import { Component } from '@angular/core';
import { Medecin } from '../services/medecin';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-analyse-valider',
  imports: [NgIf,NgForOf],
  templateUrl: './analyse-valider.html',
  styleUrl: './analyse-valider.css'
})
export class AnalyseValider {
  analyses: any[] = [];
  message = '';

  constructor(private medecinService: Medecin) {}

  ngOnInit(): void {
    this.medecinService.getAnalyseAValider().subscribe({
      next: (data) => this.analyses = data,
      error: () => this.message ="erreur lors du chargerment des analyse"
    })
  }

  valider(id: number) {
    this.medecinService.validerAnalyse(id).subscribe({
      next: () => this.message = 'Analyse validée ✅',
      error: () => this.message = 'Impossible de valider cette analyse.'
    });
  }
}
