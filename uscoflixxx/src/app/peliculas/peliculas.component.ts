import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../Service/token.service';
import { OfertasService} from '../Service/ofertas.service';
import { Oferta } from '../Modelo/oferta';


@Component({
  selector: 'app-peliculas',
  templateUrl: './peliculas.component.html',
  styleUrls: ['./peliculas.component.css']
})
export class PeliculasComponent implements OnInit {
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
}
