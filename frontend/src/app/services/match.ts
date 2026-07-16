import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Match 
{
  private apiUrl = 'http://localhost:8080/api/matchs';

  constructor (private http: HttpClient) { }

  getMatchs(): Observable<any[]> 
  {
    return this.http.get<any[]>(this.apiUrl);
  }
  creerMatch(match: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, match);
  }

  supprimerMatch(id: number): Observable<any> {
    return this.http.delete<any>(this.apiUrl + '/' + id);
  }
  
}
