import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/Modelo/Usuario';
import { Router } from '@angular/router';
import { ServiceService } from 'src/app/Service/service.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  usuario: Usuario = new Usuario();
  actualizado = false;
  failActualizado = false;
  msjErr = '';
  msjOK = '';
  failInit = false;
  constructor(private router: Router, private service: ServiceService) { }


  ngOnInit() {
    this.Editar();
  }
  Editar() {
    const id = localStorage.getItem('id');
    this.service.getPersonaId(+id)
    .subscribe(data => {
      this.usuario = data;
    });

  }
  Actualizar(usuario: Usuario) {
    this.service.updatePersona(usuario)
    .subscribe( cliente => {
      this.router.navigate(['/listar']);
      swal.fire('Cliente Actualizado', `Cliente ${usuario.nombre} actualizado con éxito!`, 'success')
    }

    )
  }
    /*this.service.updatePersona(usuario)
    .subscribe(data => {
      this.actualizado = true;
      this.failActualizado = false;
      this.msjOK = data.mensaje;
      */
  volver(): void {
    window.history.back();
  }
}
