import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Matchs } from './pages/matchs/matchs';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'matchs', component: Matchs },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];