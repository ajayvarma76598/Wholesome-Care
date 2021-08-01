import { Component, OnInit } from '@angular/core';
import {FormBuilder, NgForm} from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormControl, Validators, MinLengthValidator, FormGroup, ValidatorFn, ValidationErrors } from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import {NgbModal,ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HelplineComponent } from './../helpline/helpline.component';
import { HelplineDetailsService } from './../helpline-details.service';
@Component({
  selector: 'app-guest-details',
  templateUrl: './guest-details.component.html',
  styleUrls: ['./guest-details.component.css'],

})
export class GuestDetailsComponent implements OnInit {
  headers = ["CityName", "HelpLineNumber"];
  checkoutForm = this.formBuilder.group({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    contact: new FormControl('', [Validators.required, Validators.minLength(6)]),
    city: new FormControl('', [Validators.required])
  });
  details: any;
   closeResult: string;
  constructor( private formBuilder: FormBuilder,private modalservice: NgbModal,
               private http: HttpClient,public dialog:MatDialog,private helplinedetailsService: HelplineDetailsService) { }

  ngOnInit(): void {
    this.saveGuestDetails();
  }
  // tslint:disable-next-line:typedef
  saveGuestDetails(){
    console.log('Your data has been submitted', this.checkoutForm.value);
    let user={
      to:this.checkoutForm.controls.email.value,
      cityName:this.checkoutForm.controls.city.value
    }
    this.http.post('https://wholesome-care.stackroute.io/wellness-service/api/v1/sendingemail',user).subscribe(data => {
      let res: any=data;
      console.log("mail has been sent");
    });

    this.http.post('https://wholesome-care.stackroute.io/wellness-service/api/v1/guestuser', this.checkoutForm.value);

    if (this.checkoutForm.controls.city.value !== 'null')
    {

    let response = this.http.get('https://wholesome-care.stackroute.io/wellness-service/api/v1/helpline/' + this.checkoutForm.controls.city.value);

     response.subscribe((data) => {
      this.details = data;
      console.log(this.details);
     this.helplinedetailsService.sendCity(this.details.cityName);

     this.helplinedetailsService.sendHelpLine(this.details.helplineNumber);
    });
    this.checkoutForm.reset();
    }

  }
// tslint:disable-next-line:typedef
// open(content){
//   this.modalservice.open(content, {ariaLabelledBy: 'modal-basic-title'}).
//   result.then((result) =>
//   {this.closeResult = 'Closed with : ${result}';
// }, (reason) => {
//   this.closeResult = 'Dismissed ${this.getDismissReason(reason)}';
// });
// }

openDialog() {
  let dialogRef = this.dialog.open(HelplineComponent);
 //this.router.navigate(['/helpline', this.details]);
 }

}
