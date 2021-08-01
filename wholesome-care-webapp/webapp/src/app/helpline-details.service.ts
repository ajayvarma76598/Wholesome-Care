import { Injectable } from '@angular/core';
import { BehaviorSubject,Subject,Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class HelplineDetailsService {
  private CitySource = new BehaviorSubject<string>(null);
  currentCity = this.CitySource.asObservable();

  private helpLineSource = new BehaviorSubject<string>(null);
  currentHelpLine = this.helpLineSource.asObservable();
  private subject1= new Subject<any>();
  private subject2= new Subject<any>();


sendCity(city: string) {
  this.subject1.next({ text: city });
}
getCity(): Observable<any> {
  return this.subject1.asObservable();
}
sendHelpLine(helpline: string) {
  this.subject2.next({ text: helpline });
}
getHelpLine(): Observable<any> {
  return this.subject2.asObservable();
}

  constructor() { }
  setCity(citynam: string) {
    this.CitySource.next(citynam);
  }

   sethelpLine(helplineno: string) {
    this.helpLineSource.next(helplineno);
  }
}
