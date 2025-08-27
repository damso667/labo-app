import { NgForOf, NgIf } from '@angular/common';
import { Technicien } from './../services/technicien';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-prelevements',
  imports: [FormsModule,NgForOf,NgIf],
  templateUrl: './prelevements.html',
  styleUrl: './prelevements.css'
})
export class Prelevements implements OnInit{
 analyses: any[] = [];
  selectedAnalyseId: number | null = null;
  typePrelevement: string = '';
  message: string = '';

  constructor(private prelevementService: Technicien) {}

  ngOnInit(): void {
    this.prelevementService.getMesAnalyse().subscribe({
      next: (response) => {console.log("donnee recue", response);
        this.analyses = response.data;},
      error: (err) => console.error(err)
    });
  }

  enregistrer() {
    if (this.selectedAnalyseId && this.typePrelevement.trim()) {
      this.prelevementService.enregistrerPrelevement(this.selectedAnalyseId, this.typePrelevement)
        .subscribe({
          next: () => {
            this.message = "✅ Prélèvement enregistré avec succès !";
            // Retire l'analyse de la liste
            this.analyses = this.analyses.filter(a => a.id !== this.selectedAnalyseId);
            this.selectedAnalyseId = null;
            this.typePrelevement = '';
          },
          error: (err) => {
            console.error(err);
            this.message = "❌ Erreur lors de l'enregistrement.";
          }
        });
    }
  }
}
