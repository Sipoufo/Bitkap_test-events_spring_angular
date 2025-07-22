import {Injectable} from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  private _keycloak: Keycloak | undefined;

  get keycloak() {
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'https://sso.bitkap.africa',
        realm: 'bitkap_dev',
        clientId: 'angolar_test'
      });
    }
    return this._keycloak;
  }

  async init() {
    const authenticated = await this.keycloak.init({
      onLoad: 'check-sso',
    });
  }

  login() {
    return this.keycloak.login({redirectUri: 'http://localhost:4200'});
  }

  register() {
    return this.keycloak.register({redirectUri: 'http://localhost:4200'});
  }

  logout() {
    console.log("test");
    return this.keycloak.logout({redirectUri: 'http://localhost:4200'});
  }
}
