import { Router, RouterModule, Routes } from '@angular/router';
import { Login } from './login/login';
import { PatientsList } from './patients-list/patients-list';
import { MesPatients } from './mes-patients/mes-patients';
import { AnalysesPrescrire } from './analyses-prescrire/analyses-prescrire';
import { Prelevements } from './prelevements/prelevements';
import { Resultats } from './resultats/resultats';
import { Reactifs } from './reactifs/reactifs';
import { TypeExamens } from './type-examens/type-examens';
import { Home } from './home/home';
import { AnalysesAFaire } from './analyses-a-faire/analyses-a-faire';
import { AnalyseValider } from './analyse-valider/analyse-valider';
import { NgModule, Component } from '@angular/core';
import { Dashbord } from './dashbord/dashbord';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
{path:'dashbord', component: Dashbord},
  // Médecin
  { path: 'patients', component: PatientsList },
  { path: 'mes-patients', component: MesPatients },
  { path: 'analyses-prescrire', component: AnalysesPrescrire },
  { path: 'analyses-valider', component: AnalyseValider },

  // Technicien
  { path: 'analyses-a-faire', component: AnalysesAFaire },
  { path: 'prelevements', component: Prelevements },
  { path: 'resultats', component: Resultats },

  // Référentiels
  { path: 'reactifs', component: Reactifs },
  { path: 'type-examens', component: TypeExamens },

  // Dashboard / Home
  { path: 'home', component: Home },

  // Catch-all
  { path: '**', redirectTo: 'login' }
];


