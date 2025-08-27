import { AsyncPipe, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { LoaderService } from '../services/loader-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-loader',
  imports: [NgIf,AsyncPipe],
  templateUrl: './loader.html',
  styleUrl: './loader.css'
})
export class Loader {
  loading$!:Observable<boolean>;

  constructor(private loaderService:LoaderService) {
   this.loading$ = this.loaderService.loading$;
  }
}
