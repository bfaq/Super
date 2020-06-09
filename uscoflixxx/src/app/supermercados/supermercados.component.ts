import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../Service/token.service';
import { SupermercadoService} from '../Service/supermercado.service';
import { Supermercado } from '../Modelo/Supermercado';

@Component({
  selector: 'app-supermercados',
  templateUrl: './supermercados.component.html',
  styleUrls: ['./supermercados.component.css']
})
export class SupermercadosComponent implements OnInit {

  info: any = {};
  supermercado: Supermercado[] = [];
  constructor(private supermercadoService: SupermercadoService, private tokenService: TokenService, private router: Router) { }

  ngOnInit() {
    this.cargarSuper();
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
}

