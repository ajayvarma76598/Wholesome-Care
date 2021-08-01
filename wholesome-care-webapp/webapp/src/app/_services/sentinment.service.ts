import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SentinmentService {
  URL ='https://wholesome-care.stackroute.io/sentiment-service/api/v1/wellnessResource';
  API_KEY ="AIzaSyCpiKXLNJRb5JY6H5_557nwKzcGlutfNhA";

  URL2 = `https://www.googleapis.com/youtube/v3/search?key=${this.API_KEY}`;
      
  constructor(private _http: HttpClient) { }

 getKeyword(data) {
    return this._http.post(`${this.URL}`, data);

  }

  getResource(data){
    console.log(data);
    return this._http.get(`${this.URL2+"&part=snippet&q="+data+"&maxResults=1&type=video"}`, data);
  }
}