import { NgIf, NgForOf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { TypeExamen } from '../services/type-examen';

@Component({
  selector: 'app-type-examens',
  imports: [NgIf,NgForOf],
  templateUrl: './type-examens.html',
  styleUrl: './type-examens.css'
})
export class TypeExamens implements OnInit {
  examens: any[] = [];
  message = '';

  constructor(private service: TypeExamen) {}

  ngOnInit(): void {
    this.service.getAll().subscribe({
      next: (res: any) => this.examens = res.data ?? res,  // selon ta réponse API
      error: () => this.message = 'Erreur de chargement des types.'
    });
  }
}

