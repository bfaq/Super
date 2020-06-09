import { Component, OnInit } from '@angular/core';
import { OfertasService } from '../../Service/ofertas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Oferta } from '../../Modelo/oferta';
import swal from 'sweetalert2';
import { SupermercadoService } from 'src/app/Service/supermercado.service';
import { Supermercado } from 'src/app/Modelo/Supermercado';
import { Tipo } from 'src/app/Modelo/Tipo';
import { TipoOfertasService } from 'src/app/Service/tipoOfertas.service';
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
  supermercado: Supermercado[] = [];
  tipooferta: Tipo[] = [];
  failInit = false;

  constructor(private supermercadoService: SupermercadoService, private tipoService: TipoOfertasService,
    private ofertaService: OfertasService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.ofertaService.detalle(id).subscribe( data => {
      this.form.imagen  = data.imagen;
      this.form.nombreProducto = data.nombreProducto;
      this.form.descripcion = data.descripcion;
      this.form.tipo = data.tipo.descripcion;
      this.form.supermercado = data.supermercado.nombreSuper;
      this.form.precio = data.precio;
      this.form.vigencia = data.vigencia;
    },
      (err: any) => {
        this.failInit = true;
        this.router.navigate(['']);
      }
    );
    this.cargarSuper();
    this.cargarTipo();
  }
  cargarTipo(): void{
    this.tipoService.lista().subscribe(data =>{

    this.tipooferta = data;
  },
    (err: any) => {
      console.log(err);
    }
    );
  }
  cargarSuper(): void{
    this.supermercadoService.lista().subscribe(data =>{

    this.supermercado = data;
  },
    (err: any) => {
      console.log(err);
    }
    );
  }
  onUpdate(ofertas: Oferta): void {
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
