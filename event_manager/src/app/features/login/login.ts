import { Component } from '@angular/core';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-login',
  imports: [],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  constructor(
    private keycloakService: KeycloakService
  ) {
  }

  async ngOnInit(): Promise<void> {
    await this.keycloakService.login();
  }
}
