import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { FormControl, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-nutrition',
  templateUrl: './nutrition.component.html',
  styleUrls: ['./nutrition.component.css']
})
export class NutritionComponent implements OnInit {
  foodNutrientDetails:any;
  calorieBurntFromActivitiesDetails:any;
  errormessage:any;
  foodInputForm = new FormGroup({
    foodConsumedInput: new FormControl('', Validators.required),
  });
  constructor(private _http:HttpClient) { }
  inputSentFood:any;
  // displayedColumns: String[] = ['Food Name', 'Amount', 'Sugar', 'Fiber', 'Sodium', 'Potessium', 'Saturated Fat', 'Total Fat', 'Cholesterol', 'Protein', 'Carbohydrates', 'Calorie'];
  // displayedColumns: String[] = ['Food Name', 'Amount', 'Sugar', 'Total Fat', 'Cholesterol', 'Protein', 'Carbohydrates', 'Calorie'];
  displayedColumns: String[] = ['Food Name', 'Amount', 'Sugar', 'Total Fat', 'Protein', 'Carbohydrates', 'Calorie'];
  ngOnInit(): void {
  }
  isNutritionChatBot:boolean=false;
  isCalorieCountChatBot:boolean=false;
  nutritionChatBot(){
    this.isNutritionChatBot=true;
    this.isCalorieCountChatBot=false;
  }
  calorieCounterChatBot(){
    this.isCalorieCountChatBot=true;
    this.isNutritionChatBot=false;
  }
  // changeValue(value){value = this.foodInputForm.value.foodConsumedInput}
  foodConsumedTake(){
    this.inputSentFood = this.foodInputForm.value.foodConsumedInput;
    console.log(this.foodInputForm.value.foodConsumedInput);
    this._http.get("https://wholesome-care.stackroute.io/nlp-service/api/v1/nutrition?foodConsumed="+this.foodInputForm.value.foodConsumedInput)
            .subscribe((data)=>{
              this.foodNutrientDetails=data;
            },
            (err)=>{
              console.log(err);
              // this.errormessage = err.message;
            });
  }
  // foodConsumedTake(foodConsumedDetails){
  //   this._http.get("http://localhost:8096/wholesome-care/api/v1/nlpResponse?foodConsumed="+foodConsumedDetails)
  //           .subscribe((data)=>{
  //             this.foodNutrientDetails=data;
  //           });
  // }

  getTotalAmountFoodIntake() {
    return this.foodNutrientDetails.map(foodIntake => foodIntake.serving_size_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalSugar() {
    return this.foodNutrientDetails.map(food => food.sugar_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalFiber() {
    return this.foodNutrientDetails.map(food => food.fiber_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalSodium() {
    return this.foodNutrientDetails.map(food => food.sodium_mg).reduce((acc, value) => acc + value, 0);
  }

  getTotalPotessium() {
    return this.foodNutrientDetails.map(food => food.potassium_mg).reduce((acc, value) => acc + value, 0);
  }

  getTotalSaturatedFat() {
    return this.foodNutrientDetails.map(food => food.fat_saturated_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalFat() {
    return this.foodNutrientDetails.map(food => food.fat_total_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalCholesterol() {
    return this.foodNutrientDetails.map(food => food.cholesterol_mg).reduce((acc, value) => acc + value, 0);
  }

  getTotalProtein() {
    return this.foodNutrientDetails.map(food => food.protein_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalCarbohydrates() {
    return this.foodNutrientDetails.map(food => food.carbohydrates_total_g).reduce((acc, value) => acc + value, 0);
  }

  getTotalCalorie() {
    return this.foodNutrientDetails.map(food => food.calories).reduce((acc, value) => acc + value, 0);
  }
  inputSentActivity:any;
  activityFormInput = new FormGroup({
    activitydetailsInput: new FormControl('', Validators.required),
  });
  displayedColumns1: String[] = ['Activity Name', 'Time Duration', 'Time Unit', 'Calorie Burnt'];

  // calorieBurntFromActivities(activityDetail){
  //   this._http.get("http://localhost:8096/wholesome-care/api/v1/nlpCalorieBurnt?activityDetail="+activityDetail)
  //           .subscribe((data)=>{
  //             this.calorieBurntFromActivitiesDetails=data;
  //           });
  // }
  calorieBurntFromActivities(){
    this.inputSentActivity = this.activityFormInput.value.activitydetailsInput;
    this._http.get("https://wholesome-care.stackroute.io/nlp-service/api/v1/calorieBurnt?activityDetail="+this.activityFormInput.value.activitydetailsInput)
            .subscribe((data)=>{
              this.calorieBurntFromActivitiesDetails=data;
            },
            (err)=>{
              this.errormessage = err.error;
              console.log(err.error);
            });
  }

  getTotalCalorieBurtByActivities() {
    return this.calorieBurntFromActivitiesDetails.map(calorie => calorie.calorieBurnt).reduce((acc, value) => acc + value, 0);
  }






  
}
