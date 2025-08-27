import { NgClass, NgForOf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Auth } from '../services/auth';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-dashbord',
  imports: [FormsModule,NgForOf,NgClass],
  templateUrl: './dashbord.html',
  styleUrl: './dashbord.css'
})
export class Dashbord  implements OnInit{
  role: 'ROLE_MEDECIN' | 'ROLE_TECHNITIEN' | 'ROLE_SECRETAIRE' | null = null;
  actions: { title: string; icon: string; route: string; color: string; }[] = [];

  constructor(private auth: Auth, private router: Router) {}

  ngOnInit(): void {
    this.role = this.auth.getRole?.() as 'ROLE_MEDECIN' | 'ROLE_TECHNITIEN' | 'ROLE_SECRETAIRE' | null;
    this.loadActions();
  }

  loadActions() {
    if (this.role === 'ROLE_MEDECIN') {
      this.actions = [
        { title: '🧒 Patients', icon: 'bi bi-people', route: '/patients', color: 'primary' },
        { title: '👨‍👩‍👧‍👦 Mes Patients', icon: 'bi bi-person-lines-fill', route: '/mes-patients', color: 'info' },
        { title: 'Prescrire', icon: 'bi bi-prescription', route: '/analyses-prescrire', color: 'success' },
        { title: '✔ Resultat-Valider', icon: 'bi bi-check-circle', route: '/analyses-valider', color: 'warning' },
        { title: ' 🧬 Résultats', icon: 'bi bi-file-earmark-text', route: '/resultats', color: 'dark' },
        { title: '💉 Type Examens', icon: 'bi bi-clipboard-data', route: '/type-examens', color: 'dark' },
      ];
    } else if (this.role === 'ROLE_TECHNITIEN') {
      this.actions = [
        { title: '🧬 Analyses à faire', icon: 'bi bi-flask', route: '/analyses-a-faire', color: 'primary' },
        { title: '💉 Prélèvements', icon: 'bi bi-droplet', route: '/prelevements', color: 'info' },
        { title: '🧬 Résultats', icon: 'bi bi-file-earmark-medical', route: '/resultats', color: 'success' },
        // { title: 'Réactifs', icon: 'bi bi-box-seam', route: '/reactifs', color: 'warning' },

      ];
    }else if(this.role === 'ROLE_SECRETAIRE'){
      this.actions = [
       { title: '🧪 Reactif', icon: 'bi bi-flask', route: '/reactifs', color: 'primary' },
       { title: '💉 Type Examens', icon: 'bi bi-clipboard-data', route: '/type-examens', color: 'dark' },
      ]
    }
  }

  goTo(route: string) {
    this.router.navigate([route]);
  }
}
