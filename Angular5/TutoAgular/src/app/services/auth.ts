import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Route, Router } from '@angular/router';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Auth {
    private apiUrl = 'http://localhost:8080/api'; // ton API Spring Boot
  private roleSubject = new BehaviorSubject<string | null>(null);
  role$ = this.roleSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/login`,
      { email, password },
      { withCredentials: true } // ⚠️ important pour les cookies de session
    ).pipe(
      tap((res: any) => {
        if (res.role) {
          localStorage.setItem('role', res.role);
          this.roleSubject.next(res.role);
        }
      })
    );
  }

  logout(): Observable<any> {
    return this.http.post(`${this.apiUrl}/auth/logout`, {}, { withCredentials: true })
      .pipe(
        tap(() => {
          localStorage.removeItem('role');
          this.roleSubject.next(null);
        })
      );
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  isLoggedIn(): boolean {
    return this.getRole() !== null;
  }
    checkSession() {
    // Appel vers le backend pour vérifier si la session est active
    this.http.get('/api/session-status', { withCredentials: true }).subscribe({
      next: (res: any) => {
        if (!res.active) {
          this.router.navigate(['/login']);
        }
      },
      error: () => {
        // Si erreur (ex: session perdue), rediriger vers login
        this.router.navigate(['/login']);
      }
    });
  }
}
