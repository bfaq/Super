import { Component, OnInit } from '@angular/core';
import { Oferta } from '../../Modelo/oferta';
import { Router } from '@angular/router';
import { OfertasService } from '../../Service/ofertas.service';
import swal from 'sweetalert2';
import { SupermercadoService} from '../../Service/supermercado.service';
import { Supermercado } from '../../Modelo/Supermercado';
import { Tipo } from 'src/app/Modelo/Tipo';
import { TipoOfertasService } from 'src/app/Service/tipoOfertas.service';
@Component({
  selector: 'app-nuevo',
  templateUrl: './nuevo.component.html',
  styleUrls: ['./nuevo.component.css']
})

export class NuevoComponent implements OnInit {
  form: any = {};
  oferta: Oferta;
  creado = false;
  failProducto = false;
  mensajeFail = '';
  mensajeOK = '';
  supermercado: Supermercado[] = [];
  tipoferta: Tipo[] = [];
  constructor(private tipoService: TipoOfertasService, private supermercadoService: SupermercadoService,
  private ofertasService: OfertasService, private router: Router) { }

  ngOnInit() {
    this.cargarSuper();
    this.cargaTipo();
  }
  cargaTipo(): void{
    this.tipoService.lista().subscribe(dato =>{
    this.tipoferta = dato;
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
  onCreate(): void {
    this.ofertasService.crear(this.form).subscribe(data => {
      swal.fire('¡Nueva oferta insertada con éxito!', 'success');
      this.creado = true;
      this.failProducto = false;
      this.router.navigate(['/ofertas']);
    },
      (err: any) => {
        this.mensajeFail = err.error.mensaje;
        this.creado = false;
        this.failProducto = true;
      }
    );
  }

  volver(): void {
    window.history.back();
  }

}
