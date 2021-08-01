import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Mentor } from '../mentor-profile/Mentor';

@Injectable({
  providedIn: 'root'
})
export class MentorServiceService implements OnInit{

  baseURL:string ='https://wholesome-care.stackroute.io/wellness-mentor-service/api/v1/mentor';
  headers={
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
}
  constructor(public http:HttpClient) { 

  }

  addMentor(mentor:Mentor):Observable<any>{
    const body=JSON.stringify(mentor);
   return this.http.post(this.baseURL,body,this.headers);
  }
  updateMentor(mentor:Mentor):Observable<any>{
    const body=JSON.stringify(mentor);
    console.log(body);
    return this.http.put('https://wholesome-care.stackroute.io/wellness-mentor-service/api/v1/mentor',body,this.headers)
  }

  getMentor(email:String):Observable<Mentor>{
    return this.http.get<Mentor>('https://wholesome-care.stackroute.io/wellness-mentor-service/api/v1/mentor/'+email);

  }
  ngOnInit(): void {
    
  }
}
