import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Oferta } from '../Modelo/oferta';

const cabecera = {headers: new HttpHeaders({'Content-Type': ' application/json'})};
@Injectable({
  providedIn: 'root'
})
export class OfertasService {
  productoURL = 'http://localhost:8080/uscoflixxx-1/ofertas/';
  constructor(private httpClient: HttpClient) { }
  public lista(): Observable<Oferta[]> {
    return this.httpClient.get<Oferta[]>(this.productoURL + 'lista', cabecera);
  }
  public detalle(id: number): Observable<Oferta> {
    return this.httpClient.get<Oferta>(this.productoURL + `detalle/${id}`, cabecera);
  }
  public crear(producto: Oferta): Observable<any> {
    return this.httpClient.post<any>(this.productoURL + 'nuevo', producto, cabecera);
  }

  public editar(oferta: Oferta, id: number): Observable<any> {
    return this.httpClient.put<any>(this.productoURL + `editar/${id}`, oferta, cabecera);
  }

  public borrar(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.productoURL + `borrar/${id}`, cabecera);
  }
}
