import { Component, OnInit } from '@angular/core';
import { OfertasService } from '../../Service/ofertas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Oferta } from '../../Modelo/oferta';
import swal from 'sweetalert2';
@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {
  form: any = {};
  actualizado = false;
  failActualizado = false;
  msjErr = '';
  msjOK = '';

  failInit = false;

  constructor(private ofertaService: OfertasService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.ofertaService.detalle(id).subscribe( data => {
      this.form.imagen  = data.imagen;
      this.form.nombreProducto = data.nombreProducto;
      this.form.descripcion = data.descripcion;
      this.form.tipo = data.tipo;
      this.form.supermercado = data.supermercado;
      this.form.precio = data.precio;
      this.form.vigencia = data.vigencia;
    },
      (err: any) => {
        this.failInit = true;
        this.router.navigate(['']);
      }
    );
  }

  onUpdate(ofertas : Oferta): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.ofertaService.editar(this.form, id).subscribe( data => {

      this.router.navigate(['/ofertas']);
      swal.fire('Oferta Actualizada', `Oferta ${ofertas.nombreProducto} actualizada con éxito!`, 'success');
    },
    (err: any) => {
      this.actualizado = false;
      this.failActualizado = true;
      this.msjErr = err.error.mensaje;
    }
    );
  }

  volver(): void {
    window.history.back();
  }

}
