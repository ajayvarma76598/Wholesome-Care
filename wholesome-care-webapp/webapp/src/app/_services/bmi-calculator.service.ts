import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../common/app.constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class BmiCalculatorService {

  constructor(private http: HttpClient) { }

  getBMIScore(details): Observable<any> {
    return this.http.post(AppConstants.BMI_BASE_URL, {
      weight:details.weight,
      weightUnit:details.weightUnit,
      height:details.height,
      heightUnit:details.heightUnit
    }, httpOptions);
  }
}
