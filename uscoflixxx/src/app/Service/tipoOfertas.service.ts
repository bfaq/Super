import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Tipo } from '../Modelo/Tipo';
import { Observable } from 'rxjs';
const cabecera = {headers: new HttpHeaders({'Content-Type': ' application/json'})};
@Injectable({
  providedIn: 'root'
})
export class TipoOfertasService {

  productoURL = 'http://localhost:8080/uscoflixxx-1/ofertas/';
  constructor(private httpClient: HttpClient) { }
  public lista(): Observable<Tipo[]> {
    return this.httpClient.get<Tipo[]>(this.productoURL + 'listaTipo', cabecera);
  }
}
