import { Injectable } from '@angular/core';
import { ApiConfiguration } from '../../../config/api-configuration';
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { PageRequest } from '../../../models/page-request';
import { RequestBuilder } from '../../../request-builder';
import { filter, map, Observable } from 'rxjs';
import { StrictHttpResponse } from '../../auth/models/strict-http-response';
import { PageResponse } from '../../../models/page-response';
import { CommentResponse } from '../models/comment-response';
import { CommentRequest } from '../models/comment-request';
import { SimpleResponse } from '../../../models/simple-response';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(
    private config: ApiConfiguration,
    private http: HttpClient,
  ) {}
  
  findAllComments(params?: PageRequest, eventId?: number, context?: HttpContext): Observable<PageResponse<CommentResponse>> {
    const rb = new RequestBuilder(this.config.rootUrl, "/comment/event/"+eventId, 'get');
    if (params) {
      rb.query('page', params.page, {});
      rb.query('size', params.size, {});
    }

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<PageResponse<CommentResponse>>;
      })
    ).pipe(
      map((r: StrictHttpResponse<PageResponse<CommentResponse>>): PageResponse<CommentResponse> => r.body)
    );
  }
  
  addComment(bodyRequest?: CommentRequest, context?: HttpContext): Observable<SimpleResponse<CommentResponse>> {
    const rb = new RequestBuilder(this.config.rootUrl, "/comment", 'post');
    if (bodyRequest) {
      rb.body(bodyRequest, 'application/json');
    }

    return this.http.request(
      rb.build({ responseType: 'json', accept: 'application/json', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<SimpleResponse<CommentResponse>>;
      })
    ).pipe(
      map((r: StrictHttpResponse<SimpleResponse<CommentResponse>>): SimpleResponse<CommentResponse> => r.body)
    );
  }
}
