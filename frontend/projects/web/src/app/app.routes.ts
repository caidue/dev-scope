import { Routes } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { CardsComponent } from './views/cards/cards.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'cards', component: CardsComponent },
    { path: '', redirectTo: 'cards', pathMatch: 'full' },
];
