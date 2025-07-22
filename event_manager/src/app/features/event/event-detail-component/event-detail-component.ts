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

@Component({
  selector: 'app-event-detail-component',
  imports: [DatePipe, CommonModule, FormsModule],
  templateUrl: './event-detail-component.html',
  styleUrl: './event-detail-component.css'
})
export class EventDetailComponent implements OnInit {
  commentResponse: PageResponse<CommentResponse> = {};
  eventResponse?: SimpleResponse<EventResponse>;
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
    private router: Router
  ) {}

  ngOnInit(): void {
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
}
