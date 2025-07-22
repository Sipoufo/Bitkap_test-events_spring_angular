import { Routes } from '@angular/router';
import { EventComponent } from './features/event/event-component/event-component';
import { EventForm } from './features/event/event-form/event-form';
import { authGuard } from './services/guard/auth.guard';
import { PageNotFound } from './features/page-not-found/page-not-found';
import { EventDetailComponent } from './features/event/event-detail-component/event-detail-component';
import { Login } from './features/login/login';
import { Register } from './features/register/register';

export const routes: Routes = [
    {
        path: 'login',
        component: Login
    },
    {
        path: 'register',
        component: Register
    },
    { 
        path: '', 
        component: EventComponent 
    },
    { 
        path: 'current-user', 
        component: EventComponent 
    },
    { 
        path: 'create', 
        component: EventForm,
        canActivate: [authGuard],
    },
    { 
        path: 'detail/:eventId', 
        component: EventDetailComponent,
        canActivate: [authGuard],
    },
    { 
        path: 'edit/:eventId', 
        component: EventForm,
        canActivate: [authGuard],
    },
    { 
        path: 'my-event', 
        component: EventComponent,
        canActivate: [authGuard],
    },
    { 
        path: '**', 
        component: PageNotFound,
    },
];
