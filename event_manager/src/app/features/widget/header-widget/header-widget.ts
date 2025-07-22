import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-widget',
  imports: [CommonModule],
  templateUrl: './header-widget.html',
  styleUrl: './header-widget.css'
})
export class HeaderWidget {
  token?: string;

  constructor(
    private keycloakService: KeycloakService,
    private router: Router
  ) {
    this.token = this.keycloakService.keycloak.token;
  }

  async login() {
    await this.keycloakService.login();
  }

  async register() {
    await this.keycloakService.register();
  }

  async logout() {
    await this.keycloakService.logout();
  }
  
  allEventPage() {
    this.router.navigate(['']);
  }
  
  createEventPage() {
    this.router.navigate(['create']);
  }
}
