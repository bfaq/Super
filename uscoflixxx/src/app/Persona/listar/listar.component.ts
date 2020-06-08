import { Component, OnInit } from '@angular/core';
import {ServiceService} from '../../Service/service.service';
import { Usuario } from 'src/app/Modelo/Usuario';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.css']
})
export class ListarComponent implements OnInit {
  usuarios: Usuario[];
  creado = false;
  failProducto = false;
  mensajeFail = '';
  mensajeOK = '';
  constructor(private service: ServiceService , private router: Router) { }

  ngOnInit() {
    this.service.getPersonas().subscribe(data => {this.usuarios = data; });
  }
  Editar(usuarios: Usuario): void {
    localStorage.setItem('id', usuarios.id.toString());
    this.router.navigate(['edit']);
  }
  /*
  Delete(usuarios: Usuario) {
    if (confirm('¿Estás seguro?')){
      this.service.deletePersona(usuarios)
      .subscribe(data => {
        this.usuarios = this.usuarios.filter(p => p !== usuarios);
        alert('Usuario eliminado...');
      });
    }
  }
  */

 Delete(usuarios: Usuario): void{
  const swalWithBootstrapButtons = swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success m-3',
      cancelButton: 'btn btn-danger m-3'
    },
    buttonsStyling: false
  });

  swalWithBootstrapButtons.fire({
    title: 'Está Seguro?',
    text: `¿Seguro que desea eliminar el cliente ${usuarios.nombre}?`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Si, eliminar',
    cancelButtonText: 'No, cancelar',
    reverseButtons: true
  }).then((result) => {
    if (result.value) {
      this.service.deletePersona(usuarios)
      .subscribe(data => {
        console.log(data);
          this.usuarios = this.usuarios.filter(p => p !== usuarios);
          swal.fire(
          'Cliente Eliminado!',
          `Cliente ${usuarios.nombre} eliminado con éxito.`,
          'success'
          );
        }
      )
    }
  });
}

}
