import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs';
import {Credito} from '../models/credito.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = environment.apiUrl; // Agora pega do environment

  constructor(private http: HttpClient) {}

  searchByNumeroNfse(numeroNfse: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.baseUrl}/api/creditos/${numeroNfse}`);
  }

  searchByNumeroCredito(numeroCredito: string): Observable<Credito[]> {
    return this.http.get<Credito[]>(`${this.baseUrl}/api/creditos/credito/${numeroCredito}`);
  }

}
