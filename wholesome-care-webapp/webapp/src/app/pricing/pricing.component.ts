import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pricing',
  templateUrl: './pricing.component.html',
  styleUrls: ['./pricing.component.css']
})
export class PricingComponent implements OnInit {

  constructor(public route:Router) { }

  ngOnInit(): void {
  }
  buyNow(data:string){
    this.route.navigate(['/payment'],{queryParams:{data: JSON.stringify(data)}});
  }
}
