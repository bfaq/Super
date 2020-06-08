import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../Modelo/Usuario';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http: HttpClient) { }
  Url = 'http://localhost:8080/uscoflixxx-1/usuarios';
  getPersonas() {
    return this.http.get<Usuario[]>(this.Url);
  }
  createPersonas(usuario: Usuario) {
    return this.http.post<Usuario>(this.Url, usuario);
  }
  getPersonaId(id: number){
    return this.http.get<Usuario>(this.Url + '/' + id);
  }
  updatePersona(usuario: Usuario){
    return this.http.put<Usuario>(this.Url + '/' + usuario.id, usuario);
  }
  deletePersona(usuario: Usuario){
    return this.http.delete<Usuario>(this.Url + '/' + usuario.id);
  }
}
