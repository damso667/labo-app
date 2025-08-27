import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Technicien {
   private apiUrl = 'http://localhost:8080/api/techniciens';

  constructor(private http: HttpClient) {}

  // GET /api/techniciens/analyses-a-faire
  getAnalysesAFaire(): Observable<any> {
    return this.http.get(`${this.apiUrl}/analyses-a-faire`, { withCredentials: true });
  }

  // GET /api/techniciens/mes-analyses

   getMesAnalyse():Observable<any>{
    return this.http.get(`${this.apiUrl}/mes-analyses`, { withCredentials: true });

   }

  // POST /api/techniciens/analyses/{id}/prendre
  prendreAnalyse(id: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/analyses/${id}/prendre`, {}, { withCredentials: true });
  }


  // POST /api/techniciens/analyses/{id}/prelevements
  enregistrerPrelevement(id: number, typePrelevement: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/analyses/${id}/prelevements?typePrelevement=${typePrelevement}`, {withCredentials: true})

  }

  // PATCH /api/techniciens/analyses/{id}/resultats
  saisirResultats(id: number, resultats: string): Observable<any> {
    return this.http.patch(`${this.apiUrl}/analyses/${id}/resultats`, resultats, { withCredentials: true });
  }
}
