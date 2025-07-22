import { Routes } from '@angular/router';
import { EventComponent } from './features/event/service/event-component/event-component';
import { EventForm } from './features/event/event-form/event-form';
import { authGuard } from './services/guard/auth.guard';
import { PageNotFound } from './features/page-not-found/page-not-found';

export const routes: Routes = [
    { path: '', component: EventComponent },
    { path: 'create', component: EventForm, canActivate: [authGuard] },
    { path: 'my-event', component: EventComponent, canActivate: [authGuard] },
    { path: '**', component: PageNotFound },
];
