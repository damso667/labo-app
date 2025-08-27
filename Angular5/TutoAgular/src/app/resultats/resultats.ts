import { NgIf, NgForOf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Auth } from '../services/auth';
import { Technicien } from '../services/technicien';
import { Medecin } from '../services/medecin';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-resultats',
  imports: [NgIf,NgForOf,FormsModule],
  templateUrl: './resultats.html',
  styleUrl: './resultats.css'
})
export class Resultats implements OnInit {
  role: 'ROLE_MEDECIN'|'ROLE_TECHNITIEN'|null = null;

  // liste des analyses selon le rôle
  analyses: any[] = [];
  analysese: any[] = [];

  // formulaire technicien
  selectedAnalyseId: number | null = null;
  resultatsTexte = '';
  message = '';

  constructor(
    private auth: Auth,
    private techService: Technicien,
    private medService: Medecin
  ){}

  ngOnInit(): void {
    this.role = this.auth.getRole?.()  as 'ROLE_MEDECIN'|'ROLE_TECHNITIEN'|null ;
    this.chargerAnalyses();
  }

  chargerAnalyses() {
    if (this.role === 'ROLE_TECHNITIEN') {
      // TODO: adapte à ton API si besoin
      this.techService.getMesAnalyse().subscribe({
        next: (res: any) => this.analyses = res.data ?? res,
        error: () => this.message = 'Erreur de chargement des analyses en cours.'
      });


    } else if (this.role === 'ROLE_MEDECIN') {
      // TODO: endpoint côté back pour lister les analyses complétées à valider / consulter
      this.medService.getAnalyseAValider().subscribe({
        next: (res: any) => this.analyses = res.data ?? res,
        error: () => this.message = 'Erreur de chargement des résultats.'
      });
    }
  }


  // TECHNICIEN : soumettre résultat
  soumettre() {
    if (!this.selectedAnalyseId || !this.resultatsTexte.trim()) return;
    this.techService.saisirResultats(this.selectedAnalyseId, this.resultatsTexte).subscribe({
      next: () => {
        this.message = 'Résultats enregistrés ✅';
        this.resultatsTexte = '';
        this.selectedAnalyseId = null;
        this.chargerAnalyses();
      },
      error: () => this.message = 'Échec lors de l’enregistrement.'
    });
  }

  // MEDECIN : valider
  valider(id: number) {
    this.medService.validerAnalyse(id).subscribe({
      next: () => {
        this.message = 'Analyse validée ✅';
        this.chargerAnalyses();
      },
      error: () => this.message = 'Impossible de valider.'
    });
  }
}
