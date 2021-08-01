import { Component, Inject, OnInit } from '@angular/core';
import {FormBuilder, NgForm} from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import { DOCUMENT } from '@angular/common';
import { TransfereServiceService } from '../_services/transfere-service.service';
@Component({
  selector: 'app-meal-plan',
  templateUrl: './meal-plan.component.html',
  styleUrls: ['./meal-plan.component.css']
})
export class MealPlanComponent implements OnInit {
  item: any;
  title: string;
  mealData: any;
  imageUrl: any;
  mealdetails: string;
  val: number;
  currentvalue: any;
  details: any;
  imagevalue: any;
  data:any;
  constructor(private http: HttpClient, @Inject(DOCUMENT) private document: Document, private TransfereService: TransfereServiceService) { }

  ngOnInit(): void {
    this.getMealData(this.val);
    //this.useEffect();
  }
  isNutritionChatBott:boolean=false;
  // tslint:disable-next-line:typedef
  getMealData(val){
    this.data=this.TransfereService.getData();
    if(this.data!=null){
      val=this.data;
    }
    this.currentvalue = val;

    const response = this.http.get('https://api.spoonacular.com/mealplanner/generate?apiKey=3a92f708367f452aa8d9775287d372c4&timeFrame=day&targetCalories=' + val);
    response.subscribe((data) => {
      this.isNutritionChatBott=true;
    this.details = data;
    console.log(this.details);
    if (this.details != null)
    {
    this.title = 'Nutrients';
    }
    this.details.meals.map((meal) => {

    });
  });

  }
  goToUrl(content): void {
    window.open(content, '_blank');
    }


}


