import { Component, OnInit } from '@angular/core';
import { EventRequest } from '../models/event-request';
import { EventService } from '../services/event-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { DateUtil } from '../../../services/date-util';
import { PopupWidget } from "../../widget/popup/popup-widget/popup-widget";
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-event-form',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './event-form.html',
  styleUrl: './event-form.css'
})
export class EventForm implements OnInit {
  eventRequest: EventRequest = {};
  eventId?: number;
  errorMessage?: string;

  eventForm = new FormGroup({
    title: new FormControl(this.eventRequest.title, [
      Validators.required,
      Validators.maxLength(100),
    ]),
    description: new FormControl(this.eventRequest.description),
    eventDate: new FormControl(new Date(), [Validators.required]),
  });

  constructor(
    private eventService: EventService,
    private activatedRoute: ActivatedRoute,
    private modalService: NgbModal,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.eventId = this.activatedRoute.snapshot.params['eventId'];

    if (this.eventId != null) {
      this.loadEvent(this.eventId);
    }
  }

  private loadEvent(eventId: number) {
    this.eventService.findEventById(eventId).subscribe({
      next: (event) => {
        this.eventForm.patchValue({
          title: event.items?.title,
          eventDate: event.items?.eventDate,
          description: event.items?.description,
        });
      }
    });
  }

  addEvent() {    
    if (this.eventForm.valid) {
      this.eventService.addEvent({
        title: this.eventForm.value.title!,
        description: this.eventForm.value.description ?? "",
        eventDate:  DateUtil.getIsoString(this.eventForm.value.eventDate!) ?? "",
      }).subscribe({
        next: (event) => {
          this.router.navigate(['']);
        },
        error: (err) => {
          console.log(err);
          this.openModal(err.error.message);
        }
      });
    }
  }

  openModal(err: string) {
    const modalRef = this.modalService.open(PopupWidget);
    modalRef.componentInstance.message = err; 
  }
}
