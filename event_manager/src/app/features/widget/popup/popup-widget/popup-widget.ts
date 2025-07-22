import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-popup-widget',
  imports: [CommonModule],
  templateUrl: './popup-widget.html',
  styleUrl: './popup-widget.css'
})
export class PopupWidget {
  @Input()
  message: string = "";

  constructor(public activeModal: NgbActiveModal) { }

  c() {
    this.activeModal.dismiss('Cross click');
  }
}
