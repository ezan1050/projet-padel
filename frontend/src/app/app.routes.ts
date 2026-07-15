import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Matchs } from './pages/matchs/matchs';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'matchs', component: Matchs, canActivate: [authGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];