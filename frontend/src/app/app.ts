import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Padel Réservation');

  constructor(private router: Router) { }

  get nom(): string | null {
    return localStorage.getItem('nom');
  }

  get role(): string | null {
    return localStorage.getItem('role');
  }

  seDeconnecter(): void {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}