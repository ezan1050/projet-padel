import { Component, OnInit } from '@angular/core';
import { Match } from '../../services/match';

@Component({
  selector: 'app-matchs',
  imports: [],
  templateUrl: './matchs.html',
  styleUrl: './matchs.css',
})
export class Matchs implements OnInit {

  matchs: any[] = [];

  constructor(private matchService: Match) { }

  ngOnInit(): void {
    this.matchService.getMatchs().subscribe(data => {
      this.matchs = data;
    });
  }
}