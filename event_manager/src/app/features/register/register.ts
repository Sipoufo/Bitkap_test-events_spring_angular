import { Component } from '@angular/core';
import { KeycloakService } from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-register',
  imports: [],
  templateUrl: './register.html',
  styleUrl: './register.css'
})
export class Register {
  constructor(
    private keycloakService: KeycloakService
  ) {
  }

  async ngOnInit(): Promise<void> {
    await this.keycloakService.register();
  }
}
