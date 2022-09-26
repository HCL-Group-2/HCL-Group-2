import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseURL = 'https://ostrichmart-backend.azurewebsites.net/';

  constructor(private http: HttpClient) { }
  
  addOneCheckout(addfeedback :AddFeedback): Observable<any> {
  
    return this.http.post(this.baseURL + 'feedback/',AddFeedback);
  }


}
