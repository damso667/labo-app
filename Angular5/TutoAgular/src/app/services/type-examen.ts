import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TypeExamen {
  private ApiUrl = "http://localhost:8080/api/type-examens";

  constructor(private http: HttpClient){}

  getAll():Observable<any>{
    return this.http.get(this.ApiUrl,{withCredentials:true});
  }
}
