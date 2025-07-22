import { Component, OnInit } from '@angular/core';
import { EventRequest } from '../models/event-request';
import { EventService } from '../services/event-service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, DatePipe } from '@angular/common';
import { DateUtil } from '../../../services/date-util';

@Component({
  selector: 'app-event-form',
  imports: [ CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './event-form.html',
  styleUrl: './event-form.css'
})
export class EventForm implements OnInit {
  eventRequest: EventRequest = {};

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
    private router: Router
  ) {}

  ngOnInit(): void {}

  addEvent() {
    console.log(this.eventForm.value.title);
    console.log(this.eventRequest);
    
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
          console.log(err.error);
        }
      });
    }
  }
}
