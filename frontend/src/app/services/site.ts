import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Site {

  private apiUrl = 'http://localhost:8080/api/sites';

  constructor(private http: HttpClient) { }

  getSites(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}

/**
 * Ce que fait ce code (avec les parallèles Java que tu connais)

@Injectable → une annotation, comme @Service en Java. Elle dit "cette classe est un service injectable".
 Le providedIn: 'root' = "disponible dans toute l'appli".
constructor(private http: HttpClient) → de l'injection de dépendances, exactement comme ton service Java qui recevait un Repository !
Ici, Angular te fournit un HttpClient (l'outil pour faire des requêtes HTTP).
Tu ne le crées pas avec new, tu le demandes dans le constructeur.
apiUrl → l'adresse de ta route backend (celle que tu viens de tester dans le navigateur).
getSites() → la méthode qui va chercher les sites.
this.http.get<any[]>(this.apiUrl) envoie une requête GET à /api/sites, comme quand tu tapais l'URL, mais depuis le code.

Les deux notions nouvelles :

Observable → une "promesse de données à venir".
 Une requête HTTP prend un peu de temps (le backend doit répondre), donc Angular te donne un Observable : un "ticket" qui te préviendra quand les données seront arrivées.
  On verra comment l'écouter juste après.
any[] → "une liste de n'importe quoi".
 Pour l'instant on ne décrit pas la forme précise d'un site ; any = "type non précisé", [] = "c'est une liste".
 */