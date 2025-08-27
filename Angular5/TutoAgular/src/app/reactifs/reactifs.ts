import { NgForOf, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {  Reactif, ReactifRequest, Secretaire } from '../services/secretaire';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-reactifs',
  imports: [FormsModule,NgIf,NgForOf,ReactiveFormsModule],
  templateUrl: './reactifs.html',
  styleUrl: './reactifs.css'
})
export class Reactifs implements OnInit {
  reactifs: Reactif[] = [];
  newReactif: ReactifRequest = {
    typeExamenNom: '',
    typeExamenDescription: '',
    nom: '',
    code: '',
    unite: '',
    stock: 0,
    quantiteParAnalyse: 0
  };
  quantiteAugmenter: number = 0;

  constructor(private reactifService: Secretaire) {}

  ngOnInit(): void {
    this.loadReactifs();
  }

  loadReactifs() {
    this.reactifService.getAll().subscribe(data => {
      this.reactifs = data;
    });
  }

  addReactif() {
    this.reactifService.create(this.newReactif).subscribe(() => {
      this.loadReactifs();
      this.resetForm();
    });
  }

  augmenterStock(id: number) {
    if (this.quantiteAugmenter > 0) {
      this.reactifService.augmenterStock(id, this.quantiteAugmenter).subscribe(() => {
        this.loadReactifs();
        this.quantiteAugmenter = 0;
      });
    }
  }

  resetForm() {
    this.newReactif = {
      typeExamenNom: '',
      typeExamenDescription: '',
      nom: '',
      code: '',
      unite: '',
      stock: 0,
      quantiteParAnalyse: 0
    };
  }
}
