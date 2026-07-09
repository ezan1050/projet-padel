import { Component, signal, OnInit } from '@angular/core';
import { Site } from './services/site';
import { Match } from './services/match';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {

  protected readonly title = signal('Padel Réservation');
  sites: any[] = [];
  matchs: any[] = [];

  constructor(
    private siteService: Site,
    private matchService: Match
  ) { }

  ngOnInit(): void {
    this.siteService.getSites().subscribe(data => {
      this.sites = data;
    });

    this.matchService.getMatchs().subscribe(data => {
      this.matchs = data;
    });
  }
}

/**
 * Ce que fait ce code, ligne par ligne

import { Site } from './services/site'; → on importe notre service. Le chemin ./services/site pointe vers le fichier qu'on vient d'écrire.
constructor(private siteService: Site) → injection de dépendances, comme en Java ! Angular nous fournit une instance du service. On l'appelle siteService pour que ce soit clair.
protected sites: any[] = []; → une variable qui contiendra la liste des sites. Elle démarre vide ([]), et se remplira quand les données arriveront.
implements OnInit + ngOnInit() → une notion nouvelle et importante. ngOnInit est une méthode qu'Angular appelle automatiquement au démarrage du composant. C'est l'endroit idéal pour charger des données. (Ça te rappelle le CommandLineRunner de ton DataSeeder, qui s'exécutait au démarrage du backend !)
this.siteService.getSites().subscribe(data => { this.sites = data; }); → la ligne clé. Décortiquons :

getSites() renvoie un Observable (le "ticket" dont je te parlais).
.subscribe(...) veut dire « préviens-moi quand les données arrivent ». C'est comme s'abonner : on attend la réponse du backend sans bloquer.
data => { this.sites = data; } → quand les données arrivent, on les met dans notre variable sites.



L'idée essentielle : la requête HTTP prend du temps. On ne peut pas dire "donne-moi les sites" et les avoir instantanément. On dit plutôt "va les chercher, et quand tu les as, mets-les ici". C'est le principe de l'asynchrone.
--import de match---
On importe le service Match
On ajoute une variable matchs: any[] = []
Dans le constructeur, on injecte les deux services (séparés par une virgule). Un composant peut recevoir plusieurs services, exactement comme ton MatchService Java recevait deux repositories !
Dans ngOnInit, on lance deux requêtes : une pour les sites, une pour les matchs. Elles partent en parallèle et chacune remplit sa variable quand elle revient. 

*/