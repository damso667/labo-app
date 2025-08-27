import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Medecin {
    private apiUrl = 'http://localhost:8080/api/medecins';

  constructor(private http: HttpClient) {}

  // GET /api/medecins/patients
  getPatients(disponibles: boolean = true): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/patients?disponibles=${disponibles}`, { withCredentials: true });
  }

  // GET /api/medecins/mes-patient
  getMesPatients(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/mes-patient`, { withCredentials: true });
  }

  // PUT /api/medecins/patients/{id}/recuperer
  recupererPatient(id: Number): Observable<any> {
    return this.http.put(`${this.apiUrl}/patients/${id}/recuperer`, {}, { withCredentials: true });
  }

  // POST /api/medecins/analyses
  prescrireAnalyse(patientId: number, typeExamenId: number, description?: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/analyses`,
      { description },
      { params: { patientId, typeExamenId }, withCredentials: true });
  }

  // PATCH /api/medecins/analyses/{id}/valider
  validerAnalyse(id: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}/analyses/${id}/valider`, {}, { withCredentials: true });
  }

    getAnalyseAValider(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/analyses/a-valider`, { withCredentials: true });
  }

     getResultat(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/analyses/resultats`, { withCredentials: true });
  }
}
