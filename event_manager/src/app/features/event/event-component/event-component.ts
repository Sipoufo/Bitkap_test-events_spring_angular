import { Component, OnInit } from '@angular/core';
import { PageResponse } from '../../../models/page-response';
import { EventResponse } from '../models/event-response';
import { EventService } from '../services/event-service';
import { Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-event-component',
  imports: [CommonModule, DatePipe],
  templateUrl: './event-component.html',
  styleUrl: './event-component.css'
})
export class EventComponent implements OnInit {
  eventResponse: PageResponse<EventResponse> = {};
  page = 0;
  size = 5;
  pages: any = [];
  message = '';
  level: 'success' |'error' = 'success';

  constructor(
    private eventService: EventService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllEvents();
  }

  private findAllEvents() {
    this.eventService.findAllEvents({
      page: this.page,
      size: this.size
    })
      .subscribe({
        next: (events) => {
          this.eventResponse = events;
          this.pages = Array(this.eventResponse.totalPages)
            .fill(0)
            .map((x, i) => i);
        }
      });
  }

  gotToPage(page: number) {
    this.page = page;
    this.findAllEvents();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllEvents();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllEvents();
  }

  goToLastPage() {
    this.page = this.eventResponse.totalPages as number - 1;
    this.findAllEvents();
  }

  goToNextPage() {
    this.page++;
    this.findAllEvents();
  }

  get isLastPage() {
    return this.page === this.eventResponse.totalPages as number - 1;
  }

  displayEventDetail(eventId: number) {
    this.router.navigate(['detail', eventId]);
  }
}
