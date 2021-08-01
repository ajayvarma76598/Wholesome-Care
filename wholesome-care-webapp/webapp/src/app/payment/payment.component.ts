import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SucessMessageComponent } from '../sucess-message/sucess-message.component';
import { LoadingService } from '../_services/loadingservice.service';
import { PaymentServiceService } from '../_services/payment-service.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';
import { PaymentDetails } from './paymentDetails';
import { PaymentResponse } from './PaymentResponse';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {


  paymentDetails: PaymentDetails;
  paymentResponse: Observable<PaymentResponse> ;
  cardDetails: FormGroup;
  errMessage: string;
  loading$ = this.loader.loading$;
  currentUser: any;
  isCardPresent:boolean=true;
  constructor(private token: TokenStorageService, private matSnackBar:MatSnackBar,private userService: UserService,private formBuilder: FormBuilder, public httpClient: HttpClient, public dialog: MatDialog,public router:Router,private activatedRoute: ActivatedRoute,private loader:LoadingService, private paymentService: PaymentServiceService) {

  }


  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.paymentDetails = new PaymentDetails();
    this.activatedRoute.queryParams.subscribe((params)=>{
      this.paymentDetails.plan = params.data.split("\"")[1];
      this.paymentDetails.emailId = this.currentUser.email;
    })
    // this.paymentResponse = new PaymentResponse();
    this.createGroup();
  }
createGroup(){
  this.cardDetails = this.formBuilder.group({
    cardNumber: new FormControl(['', [Validators.required, Validators.maxLength(16)]]),
    expMonth: new FormControl('', [Validators.required, Validators.max(12), Validators.min(1)]),
    expYear: new FormControl('', [Validators.required, Validators.maxLength(4), Validators.minLength(4)]),
    cvc: new FormControl('', [Validators.minLength(3), Validators.maxLength(3)])
  });
}
  payCharge() {
    this.loader.show();
    this.isCardPresent = false
    this.paymentDetails.card_number = this.cardDetails.value.cardNumber;
    this.paymentDetails.cvc = this.cardDetails.value.cvc;
    this.paymentDetails.exp_month = this.cardDetails.value.expMonth;
    this.paymentDetails.exp_year = this.cardDetails.value.expYear;

    console.log(this.paymentDetails);
    this.paymentService.makeCharge(this.paymentDetails).subscribe((data) => {
      // this.paymentResponse = data;
      this.loader.hide();
      

      if (data.activeStatus) {
        this.isCardPresent= false;
        this.matSnackBar.open('Your payment is successfull', '', {
          duration: 4000,
          verticalPosition: 'top',
          panelClass: 'blue-snackbar'
        });
        sessionStorage.setItem("paymentResponse",JSON.stringify(data));

        this.router.navigate(['/questionnaire']);
      }
      else {
      this.loader.hide();
      this.isCardPresent= true;
      this.matSnackBar.open('Your payment Failed', '', {
        duration: 4000,
        // horizontalPosition: 'center',
        verticalPosition: 'top',
        panelClass: 'blue-snackbar'
      });
        this.router.navigate(['/payment']);
      }
    }, (error) => {
      this.loader.hide();
      this.isCardPresent = true;
      this.errMessage = error.error.split(";")[0];
      this.matSnackBar.open('Your payment Failed, '+this.errMessage, '', {
        duration: 4000,
        // horizontalPosition: 'center',
        verticalPosition: 'top',
        panelClass: 'blue-snackbar'
      });
    })
    this.paymentDetails = new PaymentDetails();
    this.createGroup();

  }



}
