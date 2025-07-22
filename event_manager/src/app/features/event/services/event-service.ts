import { Injectable } from '@angular/core';
import { ApiConfiguration } from '../../../config/api-configuration';
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { PageRequest } from '../../../models/page-request';
import { filter, map, Observable } from 'rxjs';
import { PageResponse } from '../../../models/page-response';
import { EventResponse } from '../models/event-response';
import { RequestBuilder } from '../../../request-builder';
import { StrictHttpResponse } from '../../auth/models/strict-http-response';
import { SimpleResponse } from '../../../models/simple-response';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  constructor(
    private config: ApiConfiguration,
    private http: HttpClient,
  ) {}

  findAllEvents(params?: PageRequest, context?: HttpContext): Observable<PageResponse<EventResponse>> {
    const rb = new RequestBuilder(this.config.rootUrl, "/event/all", 'get');
    if (params) {
      rb.query('page', params.page, {});
      rb.query('size', params.size, {});
    }

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<PageResponse<EventResponse>>;
      })
    ).pipe(
      map((r: StrictHttpResponse<PageResponse<EventResponse>>): PageResponse<EventResponse> => r.body)
    );
  }

  findBookById(eventId: number, context?: HttpContext): Observable<SimpleResponse<EventResponse>> {
    const rb = new RequestBuilder(this.config.rootUrl, "/event/"+eventId, 'get');

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<SimpleResponse<EventResponse>>;
      })
    ).pipe(
      map((r: StrictHttpResponse<SimpleResponse<EventResponse>>): SimpleResponse<EventResponse> => r.body)
    );
  }
}
