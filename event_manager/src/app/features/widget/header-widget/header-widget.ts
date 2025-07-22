import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-header-widget',
  imports: [CommonModule],
  templateUrl: './header-widget.html',
  styleUrl: './header-widget.css'
})
export class HeaderWidget {
  token?: string;

  constructor(
    private keycloakService: KeycloakService
  ) {
    console.log(this.keycloakService.keycloak);
    this.token = this.keycloakService.keycloak.token;
  }

  async login() {
    await this.keycloakService.login();
  }

  async logout() {
    await this.keycloakService.logout();
  }
}
