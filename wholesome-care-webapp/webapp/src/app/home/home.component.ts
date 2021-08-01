import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormBuilder, NgForm} from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient} from '@angular/common/http';
// import {NgbModal,ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormControl, Validators, MinLengthValidator, FormGroup, ValidatorFn, ValidationErrors } from '@angular/forms';
import * as Aos from 'aos';
import {MatCarouselComponent,MatCarousel} from "@ngmodule/material-carousel"
import { ViewEncapsulation } from '@angular/core';
import { SwiperComponent } from "swiper/angular";
// import Swiper core and required modules
import SwiperCore, { EffectCoverflow, Pagination } from "swiper/core";
import { GuestDetailsComponent } from './../guest-details/guest-details.component';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { NutritionComponent } from '../nutrition/nutrition.component';

// install Swiper modules
SwiperCore.use([EffectCoverflow, Pagination]);

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HomeComponent implements OnInit {
  headers = ["CityName", "HelpLineNumber"];
  // checkoutForm = this.formBuilder.group({
  //   name: ['', Validators.required],
  //   email: ['', Validators.required],
  //   contact: ['', Validators.required],
  //   address: ['', Validators.required],
  // });
  details: any;
   closeResult: string;
  constructor(public router:Router, private formBuilder: FormBuilder,
    private http: HttpClient,public dialog: MatDialog  ) { }

  ngOnInit(): void {
   Aos.init();
   //this.saveGuestDetails();

  }

  login(): void{
    this.router.navigate(["login"]);
  }

  botIsTalking(){
    this.dialog.open(NutritionComponent, {
      width      : '80%',
      height     : '80vh',
      hasBackdrop: true,
      maxHeight  : '700px',
      // data       : this.userDetails.plan
    });
    // this.router.navigate(["chatbot"]);
  }
  // sosroute(){
  //   this.router.navigate([""])
  // }
   // tslint:disable-next-line:typedef
  //  saveGuestDetails(){
  //   console.log('Your data has been submitted', this.checkoutForm.value);
  //   this.http.post('http://localhost:8070/api/v1/guestuser', this.checkoutForm.value);
  //   if (this.checkoutForm.controls.address.value !== 'null')
  //   {
  //   let response = this.http.get('http://localhost:8070/api/v1/helpline/' + this.checkoutForm.controls.address.value);
  //   response.subscribe((data) => {
  //     this.details = data;
  //   });
  //   this.checkoutForm.reset();
  //   }
  //   console.log(this.checkoutForm.controls.address.value);
  // }

// tslint:disable-next-line:typedef
open(content){
//   this.modalservice.open(content, {ariaLabelledBy: 'modal-basic-title'}).
//   result.then((result) =>
//   {this.closeResult = 'Closed with : ${result}';
// }, (reason) => {
//   this.closeResult = 'Dismissed ${this.getDismissReason(reason)}';
// });
}
openDialog() {
  this.dialog.open(GuestDetailsComponent);

 }
}
