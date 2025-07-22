import { Injectable } from '@angular/core';
import { ApiConfiguration } from '../../../config/api-configuration';
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { RegistrationRequest } from '../models/registration-request';
import { filter, map, Observable } from 'rxjs';
import { StrictHttpResponse } from '../models/strict-http-response';
import { RequestBuilder } from '../../../request-builder';
import { AuthenticationRequest } from '../models/authentication-request';
import { AuthenticationResponse } from '../models/authentication-response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    private config: ApiConfiguration, 
    private http: HttpClient
  ) {}
  
  registerPath = '/auth/register';

  register$Response(params: RegistrationRequest, context?: HttpContext): Observable<StrictHttpResponse<{}>> {
    const rb = new RequestBuilder(this.config.rootUrl, '/auth/register', 'post');
    if (params) {
      rb.body(params, 'application/json');
    }

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<{
        }>;
      })
    );
  }

  register(params: RegistrationRequest, context?: HttpContext): Observable<{}> {
    return this.register$Response(params, context).pipe(
      map((r: StrictHttpResponse<{}>): {} => r.body)
    );
  }

  authenticate$Response(params: AuthenticationRequest, context?: HttpContext): Observable<StrictHttpResponse<AuthenticationResponse>> {
    const rb = new RequestBuilder(this.config.rootUrl, '/auth/authenticate', 'post');
    if (params) {
      rb.body(params, 'application/json');
    }

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<AuthenticationResponse>;
      })
    );
  }

  authenticate(params: AuthenticationRequest, context?: HttpContext): Observable<AuthenticationResponse> {
    return this.authenticate$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthenticationResponse>): AuthenticationResponse => r.body)
    );
  }

  confirm$Response(token: string, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    const rb = new RequestBuilder(this.config.rootUrl, "/auth/activate-account", 'get');
    if (token) {
      rb.query('token', token, {});
    }

    return this.http.request(
      rb.build({ responseType: 'text', accept: '*/*', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  confirm(token: string, context?: HttpContext): Observable<void> {
    return this.confirm$Response(token, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }
}
