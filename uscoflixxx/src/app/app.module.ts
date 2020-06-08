import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListarComponent } from './Persona/listar/listar.component';
import { AddComponent } from './Persona/add/add.component';
import { EditComponent } from './Persona/edit/edit.component';
import {FormsModule} from '@angular/forms';
import {ServiceService} from '../app/Service/service.service';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { PeliculasComponent } from './peliculas/peliculas.component';
import { interceptorProvider } from './Service/basic-auth-htpp-intercept.service';
import { SupermercadosComponent } from './supermercados/supermercados.component';
import { DetalleComponent } from './Ofertas/detalle/detalle.component';
import { NuevoComponent } from './Ofertas/nuevo/nuevo.component';
import { EditarComponent } from './Ofertas/editar/editar.component';
import { ListarOfertaComponent } from './Ofertas/listar-oferta/listar-oferta.component';


@NgModule({
  declarations: [
    AppComponent,
    ListarComponent,
    AddComponent,
    EditComponent,
    LoginComponent,
    LogoutComponent,
    PeliculasComponent,
    SupermercadosComponent,
    DetalleComponent,
    NuevoComponent,
    EditarComponent,
    ListarOfertaComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ServiceService, interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
