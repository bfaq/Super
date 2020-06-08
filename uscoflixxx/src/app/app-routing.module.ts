
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarComponent } from './Persona/listar/listar.component';
import { AddComponent } from './Persona/add/add.component';
import { EditComponent } from './Persona/edit/edit.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService as guard} from './Service/auth-gaurd.service';
import { PeliculasComponent } from './peliculas/peliculas.component';
import { SupermercadosComponent } from './supermercados/supermercados.component';
import { ListarOfertaComponent } from './Ofertas/listar-oferta/listar-oferta.component';
import { DetalleComponent } from './Ofertas/detalle/detalle.component';
import { EditarComponent } from './Ofertas/editar/editar.component';
import { NuevoComponent } from './Ofertas/nuevo/nuevo.component';


const routes: Routes = [
  {path: 'listar', component: ListarComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'add', component: AddComponent},
  {path: 'edit', component: EditComponent,  canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent, canActivate: [guard], data: { expectedRol: ['admin', 'user']}},
  {path: 'oferta', component: PeliculasComponent},
  {path: 'supermercados', component: SupermercadosComponent},
  {path: 'ofertas', component: ListarOfertaComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'detalle/:id', component: DetalleComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'editar/:id', component: EditarComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: 'nuevo', component: NuevoComponent, canActivate: [guard], data: { expectedRol: ['admin']}},
  {path: '**', redirectTo: 'oferta', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
