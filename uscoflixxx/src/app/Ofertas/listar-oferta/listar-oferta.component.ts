import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../Service/token.service';
import { OfertasService} from '../../Service/ofertas.service';
import { Oferta } from '../../Modelo/oferta';
import swal from 'sweetalert2';
@Component({
  selector: 'app-listar-oferta',
  templateUrl: './listar-oferta.component.html',
  styleUrls: ['./listar-oferta.component.css']
})
export class ListarOfertaComponent implements OnInit {

  info: any = {};
  ofertas: Oferta[] = [];
  constructor(private ofertaService: OfertasService, private tokenService: TokenService, private router: Router) { }

  ngOnInit() {
    this.cargarOfertas();
  }
cargarOfertas(): void{
  this.ofertaService.lista().subscribe(data =>{

  this.ofertas = data;
},
  (err: any) => {
    console.log(err);
  }
  );
}
/*
onDelete(id: number): void {
  if (confirm('¿Estás seguro?')) {
    this.ofertaService.borrar(id).subscribe(data => {
      this.cargarOfertas();
    });
  }
}
*/
onDelete(ofertas: Oferta): void{
  const swalWithBootstrapButtons = swal.mixin({
    customClass: {
      confirmButton: 'btn btn-success m-3',
      cancelButton: 'btn btn-danger m-3'
    },
    buttonsStyling: false
  });

  swalWithBootstrapButtons.fire({
    title: 'Está Seguro?',
    text: `¿Seguro que desea eliminar el producto ${ofertas.nombreProducto}?`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Si, eliminar',
    cancelButtonText: 'No, cancelar',
    reverseButtons: true
  }).then((result) => {
    if (result.value) {
      this.ofertaService.borrar(ofertas.id)
      .subscribe(data => {
        console.log(data);
          this.ofertas = this.ofertas.filter(p => p !== ofertas);
          swal.fire(
          'Oferta Eliminada!',
          `Oferta ${ofertas.nombreProducto} eliminado con éxito.`,
          'success'
          );
        }
      )
    }
  });
}

}
