import {inject, Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptorFn} from '@angular/common/http';
import {KeycloakService} from '../keycloak/keycloak.service';

export const keycloakHttpInterceptor: HttpInterceptorFn = (req, next) => {
  const keycloakService = inject(KeycloakService);
  const token = keycloakService.keycloak.token;
  if (token) {
    const authReq = req.clone({
      headers: new HttpHeaders({
        Authorization: `Bearer ${token}`
      })
    });
    return next(authReq);
  }
  return next(req);
};
