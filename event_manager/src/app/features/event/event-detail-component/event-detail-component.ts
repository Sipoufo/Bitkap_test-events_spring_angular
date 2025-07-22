import { Component, OnInit } from '@angular/core';
import { PageResponse } from '../../../models/page-response';
import { CommentResponse } from '../models/comment-response';
import { EventResponse } from '../models/event-response';
import { CommonModule, DatePipe } from '@angular/common';
import { EventService } from '../services/event-service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommentService } from '../services/comment-service';
import { SimpleResponse } from '../../../models/simple-response';
import { CommentRequest } from '../models/comment-request';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faTrash, faEdit } from '@fortawesome/free-solid-svg-icons';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-event-detail-component',
  imports: [DatePipe, CommonModule, FormsModule, FontAwesomeModule],
  templateUrl: './event-detail-component.html',
  styleUrl: './event-detail-component.css'
})
export class EventDetailComponent implements OnInit {
  faTrash = faTrash;
  faEdit = faEdit;
  commentResponse: PageResponse<CommentResponse> = {};
  eventResponse?: SimpleResponse<EventResponse>;
  subject: string = "";
  private eventId = 0;
  page = 0;
  size = 5;

  commentRequest: CommentRequest = {
    value: "",
    eventId: this.eventId,
  };

  constructor(
    private eventService: EventService,
    private commentService: CommentService,
    private activatedRoute: ActivatedRoute,
    private keycloakService: KeycloakService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.subject = this.keycloakService.keycloak.subject ?? "";
    this.eventId = this.activatedRoute.snapshot.params['eventId'];
    this.commentRequest = {
      value: "",
      eventId: this.eventId,
    };

    if (this.eventId) {
      this.loadEvent();
    }
  }

  private loadEvent() {
    this.eventService.findEventById(this.eventId).subscribe({
      next: (event) => {
        this.eventResponse = event;
        this.findAllComments();
      }
    });
  }

  deleteEvent() {
    this.eventService.deleteEvent(this.eventId).subscribe({
      next: (event) => {
        this.router.navigate(['']);
      }
    });
  }

  private findAllComments() {
    this.commentService.findAllComments(
      {
        page: this.page,
        size: this.size
      },
      this.eventId
    )
      .subscribe({
        next: (comments) => {
          this.commentResponse = comments;
        }
      });
  }

  addComment() {
    this.commentService.addComment(this.commentRequest).subscribe({
      next: (comment) => {
        this.loadEvent();
      },
      error: (err) => {
        console.log(err.error);
        // this.errorMsg = err.error.validationErrors;
      }
    });
  }

  editEvent() {
    this.router.navigate(['edit', this.eventId]);
  }
}
