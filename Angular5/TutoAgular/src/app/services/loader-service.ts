import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {
  private pending = 0;
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();

  show() {
    this.pending++;
    if (this.pending === 1) this.loadingSubject.next(true);
  }

  hide() {
    if (this.pending > 0) {
      this.pending--;
      if (this.pending === 0) this.loadingSubject.next(false);
    }
  }

  /** au cas où (ex: navigation error): */
  reset() {
    this.pending = 0;
    this.loadingSubject.next(false);
  }
}
