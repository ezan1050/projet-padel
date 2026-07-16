import { Component, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Match } from '../../services/match';

@Component({
  selector: 'app-admin',
  imports: [FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.css',
})
export class Admin implements OnInit {

  matchs = signal<any[]>([]);
  message = '';

  nouveauMatch = {
    date: '',
    heureDebut: '',
    prix: 60,
    type: 'PUBLIC',
    statut: 'EN_ATTENTE',
    terrain: { id: 1 },
    organisateur: { id: 1 }
  };

  constructor(private matchService: Match) { }

  ngOnInit(): void {
    this.chargerMatchs();
  }

  chargerMatchs(): void {
    this.matchService.getMatchs().subscribe(data => {
      this.matchs.set(data);
    });
  }

  creerMatch(): void {
    this.matchService.creerMatch(this.nouveauMatch).subscribe({
      next: () => {
        this.message = 'Match cree';
        this.chargerMatchs();
      },
      error: () => {
        this.message = 'Erreur lors de la creation';
      }
    });
  }

  supprimerMatch(id: number): void {
    this.matchService.supprimerMatch(id).subscribe({
      next: () => {
        this.message = 'Match supprime';
        this.chargerMatchs();
      },
      error: (err) => {
        if (err.status === 409) {
          this.message = err.error;
        } else if (err.status === 403) {
          this.message = 'Droits insuffisants pour supprimer un match';
        } else {
          this.message = 'Erreur lors de la suppression';
        }
      }
    });
  }
}