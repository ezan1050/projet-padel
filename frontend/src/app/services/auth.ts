import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {

  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  login(matricule: string, motDePasse: string): Observable<any> {
    return this.http.post<any>(this.apiUrl + '/login', {
      matricule: matricule,
      motDePasse: motDePasse
    });
  }
}
/**
 * this.http.post(...) au lieu de .get(...) → on envoie des données au lieu d'en demander.
Le deuxième argument { matricule: ..., motDePasse: ... } est le corps de la requête — l'équivalent du -d '{"matricule":"G0001",...}' de tes commandes curl ! Tu envoies un objet JSON.
Le retour est un Observable<any> (pas any[]) car le backend renvoie un seul objet ({token, role}), pas une liste.
 */