import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassGetter } from '@angular/compiler/src/output/output_ast';
import { BmiCalculatorService } from '../_services/bmi-calculator.service';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';
import { TransfereServiceService } from '../_services/transfere-service.service';

export interface PeriodicElement {
  score: string;
  value: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { score: 'BMI =< 18.5', value : 'Under weight'},
  { score: 'BMI = 18.5-24.9', value : 'Normal weight'},
  { score: 'BMI = 25-29.9', value : 'Over weight'},
  { score: 'BMI =>30', value : 'Obese'}

];

@Component({
  selector: 'app-bmicalculator',
  templateUrl: './bmicalculator.component.html',
  styleUrls: ['./bmicalculator.component.css']
})
export class BMICalculatorComponent implements OnInit {
  displayedColumns: string[] = ['score','value'];
  dataSource = ELEMENT_DATA;
  result:any;
  errorMessage = '';
  message='';
  calories:any;
  isSubmitted:Boolean=false;

  form:FormGroup = new FormGroup({
    weight : new FormControl(''),
    height : new FormControl(''),
    weightUnit : new FormControl(''),
    heightUnit : new FormControl('')
  });
  constructor(private bmiCalculatorService : BmiCalculatorService, private http:HttpClient, private transfereService : TransfereServiceService, private router:Router) { }

  ngOnInit(): void {

  }
  onSubmit(details : NgForm){
    this.isSubmitted=true;
    console.log(details);
    console.log(this.form.value);
    this.http.post<any>('https://wholesome-care.stackroute.io/wellness-tracker-service/api/v1/bmi', { weight: this.form.value.weight, weightUnit: this.form.value.weightUnit ,height: this.form.value.height , heightUnit: this.form.value.heightUnit}).subscribe(data => {
        this.result = data.toFixed(2);
        console.log(this.result);
        if(this.result<=18.5){
          this.calories=2500;
          this.message='you are Under weight';
        }else if(this.result>18.5 && this.result<24.9){
          this.calories=2000;
          this.message='you are Normal weight';
        }else if (this.result>=25 && this.result<=29.9) {
          this.calories=1500;
          this.message='you are Over weight';
        } else if(this.result>=30){
          this.calories=1000;
          this.message='you are obese';
        }
    });
  }

  fetchMealPlan(){
    this.transfereService.setData(this.calories);
    this.router.navigateByUrl('mealplan', {skipLocationChange: false}).then(() =>
            this.router.navigate(["mealplan"]));
    
  }

}

