import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient, HttpErrorResponse, HttpEvent } from '@angular/common/http';

import { map, tap, throwError, catchError, Observable } from 'rxjs';
import { Tutorial } from '../models/tutorial.model';

interface GetResponse{
  _embedded:{
    tutorials: Tutorial[];
  }
}

@Injectable({
  providedIn: 'root'
})
export class TutorialService {  
  constructor(private http: HttpClient) { }
  
  private api_url: string = `${environment.api_url}/`;

  getAll(): Observable<Tutorial[]> {
    return this.http.get<GetResponse>(this.api_url)
    .pipe(
      map(response => response._embedded.tutorials),
      tap((response) => console.log("Response:"+response)),
      catchError((err, caught) => {
        console.error(err);
        throw err;
      }
      )
    )
  }
  get(id: any): Observable<Tutorial> {
    return this.http.get(`${environment.api_url}/${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post(environment.api_url, data);
  }
  update(id: any, data: any): Observable<any> {
    return this.http.put(`${environment.api_url}/${id}`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${environment.api_url}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(environment.api_url);
  }
  findByTitle(title: any): Observable<Tutorial[]> {
    const api_url_this= `${environment.api_url}/search/findByTitleContaining?title=${title}`;
    return this.http.get<GetResponse>(api_url_this)
    .pipe(
      map(response => response._embedded.tutorials),
      tap((response) => console.log("Response:"+response)),
      catchError((err, caught) => {
        console.error(err);
        throw err;
      }
      )
    )
  }

}