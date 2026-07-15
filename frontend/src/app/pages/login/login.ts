import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  matricule = '';
  motDePasse = '';
  message = '';

  constructor(private authService: Auth) { }

  seConnecter(): void {
    this.authService.login(this.matricule, this.motDePasse).subscribe({
      next: (reponse) => {
        localStorage.setItem('token', reponse.token);
        localStorage.setItem('role', reponse.role);
        this.message = 'Connexion reussie ! Role : ' + reponse.role;
      },
      error: () => {
        this.message = 'Matricule ou mot de passe incorrect';
      }
    });
  }
}