import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { WebsiteFeedback } from './model/WebsiteFeedback';

@Injectable({
  providedIn: 'root'
})
export class WebsiteFeedbackService {

  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';

  constructor(private http: HttpClient) { }
  
  addWebsiteFeedback(newWebsiteFeedback: WebsiteFeedback):Observable<any>{
    return this.http.post(this.baseURL + 'websitefeedback', newWebsiteFeedback);
  }
  
}

