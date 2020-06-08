import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supermercado } from '../Modelo/Supermercado';

const cabecera = {headers: new HttpHeaders({'Content-Type': ' application/json'})};
@Injectable({
  providedIn: 'root'
})
export class SupermercadoService {

  productoURL = 'http://localhost:8080/uscoflixxx-1/ofertas/';
  constructor(private httpClient: HttpClient) { }
  public lista(): Observable<Supermercado[]> {
    return this.httpClient.get<Supermercado[]>(this.productoURL + 'listaRep', cabecera);
  }
}
