import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {
  @Input() dietPlan:any;
  constructor() { }
  type = 'PieChart';
  data :any[][] ;
  options = {
    is3D:true,
    slices: {
      2: {offset: 0.2},
      4: {offset: 0.3}
    },
    legend: {position: 'none'},
    chartArea:{width:"80%",height:"90%"},
    colors: ['#52d726', '#ff7300', '#ffec00', '#ff0000'],
   width:300,
   height:300

  };
  ngOnInit(): void {
    this.data=[
      ['calorie', this.getTotalCal()],
      ['carbs',this.getTotalCarbs()],
      ['Protien', this.getTotalProtein()],
      ['Sugar', this.getTotalSugar()]
    ];
  }
  getTotalCal(){
    let calBreakfast = this.dietPlan.breakfast.map(b => b.calorie).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calMorningSnacks = this.dietPlan.morningSnacks.map(b => b.calorie).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calLunch = this.dietPlan.lunch.map(b => b.calorie).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calEveningSnacks = this.dietPlan.eveningSnacks.map(b => b.calorie).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calDinner = this.dietPlan.dinner.map(b => b.calorie).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    return calBreakfast+calMorningSnacks+calLunch+calEveningSnacks+calDinner;
  }

  getTotalCarbs(){
    let calBreakfast = this.dietPlan.breakfast.map(b => b.carbs).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calMorningSnacks = this.dietPlan.morningSnacks.map(b => b.carbs).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calLunch = this.dietPlan.lunch.map(b => b.carbs).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calEveningSnacks = this.dietPlan.eveningSnacks.map(b => b.carbs).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calDinner = this.dietPlan.dinner.map(b => b.carbs).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    return calBreakfast+calMorningSnacks+calLunch+calEveningSnacks+calDinner;
  }
  getTotalProtein(){
    let calBreakfast = this.dietPlan.breakfast.map(b => b.protein).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calMorningSnacks = this.dietPlan.morningSnacks.map(b => b.protein).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calLunch = this.dietPlan.lunch.map(b => b.protein).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calEveningSnacks = this.dietPlan.eveningSnacks.map(b => b.protein).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calDinner = this.dietPlan.dinner.map(b => b.protein).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    return calBreakfast+calMorningSnacks+calLunch+calEveningSnacks+calDinner;
  }
  getTotalSugar(){
    let calBreakfast = this.dietPlan.breakfast.map(b => b.sugar).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calMorningSnacks = this.dietPlan.morningSnacks.map(b => b.sugar).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calLunch = this.dietPlan.lunch.map(b => b.sugar).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calEveningSnacks = this.dietPlan.eveningSnacks.map(b => b.sugar).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    let calDinner = this.dietPlan.dinner.map(b => b.sugar).map((i) => Number(i)).reduce((acc, value) => acc + value, 0);
    return calBreakfast+calMorningSnacks+calLunch+calEveningSnacks+calDinner;
  }
}
