import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NuevoUsuario } from 'src/app/Modelo/nuevo-usuario';
import { AuthService } from '../../Service/auth.service';
import swal from 'sweetalert2';
@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  form: any = {};
  private usuario: any = {};
  isRegister = false;
  isRegisterFail = false;
  mensajeFail = '';
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onRegister() {
    this.usuario = new NuevoUsuario(this.form.nombre, this.form.nombreUsuario, this.form.email, this.form.password);
    this.authService.registro(this.usuario).subscribe(data => {
    swal.fire('Nuevo cliente', `Cliente ${this.usuario.nombre} creado con exito`, 'success');
    },
    (err: any) => {
      this.mensajeFail = err.error.mensaje;
      this.isRegister = false;
      this.isRegisterFail = true;
    }
    );
  }
  volver(): void {
    window.history.back();
  }
}
