import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from './Service/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'uscoflixxx';
  isLogin = false;
  roles: string[];
  authority: string;
  info: any = {};
  constructor(private tokenService: TokenService, private router: Router) {}
  /*  Listar() {
      this.router.navigate(['listar']);
    }
    Nuevo() {
      this.router.navigate(['add']);
    }
    Pelis() {
      this.router.navigate(['pelis']);
    }
  */
  ngOnInit() {
    this.info = {
      token: this.tokenService.getToken(),
      nombreUsuario: this.tokenService.getUserName(),
      authorities: this.tokenService.getAuthorities()
      };
    if (this.tokenService.getToken()) {
      this.isLogin = true;
      this.roles = [];
      this.roles = this.tokenService.getAuthorities();
      this.roles.every(rol => {
        if (rol === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        this.authority = 'user';
        return true;
      });
    }
  }
  logOut(): void {
    this.tokenService.logOut();
    this.router.navigate(['pelis']);
    window.location.reload();
    this.isLogin = false;
    this.authority = '';

  }
}
