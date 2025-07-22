import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderWidget } from "./features/widget/header-widget/header-widget";
import { FooterWidget } from "./features/widget/footer-widget/footer-widget";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderWidget, FooterWidget],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('event_manager');
}
