import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { ViewEncapsulation } from '@angular/core';
import { ConfirmBoxComponent } from '../confirm-box/confirm-box.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionnaireComponent implements OnInit {
  constructor(private _http:HttpClient, private matSnackBar:MatSnackBar, private router:Router, private matDialog: MatDialog) { }
  qnProgress:number=0;
  questions:any;
  selectedOption:any;
  questionsURL="https://wholesome-care.stackroute.io/questionnaire-service/api/v1/questions";
  selectedAnsweredURL="https://wholesome-care.stackroute.io/questionnaire-service/api/v1/answers";
  arrayStored:Array<string>=[];
  arrayStoredMental:Array<string>=[];
  mapPhysical:Array<any>=[];
  submitted:boolean=false;
  imgorder:string='row';
  questorder:number=1;
  ngOnInit(): void {
    this._http.get(this.questionsURL, {
      headers: new HttpHeaders({
        Authorization: `Bearer ${this.token}`,
      }),
    }).subscribe(data=>{
      this.questions = data;
    });
    if(sessionStorage.getItem('selectedOptions')!=null){
      this.arrayStored=JSON.parse(sessionStorage.getItem('selectedOptions'));
        this.qnProgress=this.arrayStored.length;
        if(sessionStorage.getItem('none')!=null){
          if(this.arrayStored[2]!=null && this.arrayStored[2][1]=='None of the above'){
            let isNone = JSON.parse(sessionStorage.getItem('none'));
                if(isNone==true){
                  this.checkedDisable=true;
            }
          }
        }
    }
  }
  submitQuestions:boolean=false;
  selectedCheckBox:Array<any>=[];
  checkedDisable:boolean=false;
  token=sessionStorage.getItem("auth-token");
  userName:{displayName:string, email:string, role:string}=JSON.parse(sessionStorage.getItem("auth-user"));
  selectedCHeckedBoxCheked:Array<{checked:any, value:any}>=[];
  mapAnswered=new Map();
  selectedOptions:Array<any>;
  selected:Array<any>;
  selectedStorage:Array<any>=[];
  nextQuestion(){
    sessionStorage.setItem("hello",JSON.stringify(true));
    if(sessionStorage.getItem('selectedOptions')==null){
      sessionStorage.setItem('selectedOptions', '[]');
    }else{
      this.selectedOptions=JSON.parse(sessionStorage.getItem('selectedOptions'));
      this.selectedOptions.forEach(element => {
       this.mapAnswered.set(element[0],element[1]);
      });
    }
    this.mapAnswered.set(this.qnProgress, this.selectedOption);
    sessionStorage.setItem('selectedOptions', JSON.stringify(Array.from(this.mapAnswered)));
    this.qnProgress++;
    if(this.qnProgress==7){
      this.matSnackBar.open('You have completed physical wellness questions...!!! "All progress takes place outside the comfort zone."', '', {
        duration: 4000,
        // horizontalPosition: 'center',
        verticalPosition: 'top',
        panelClass: 'blue-snackbar'
      });
      this.imgorder='row-reverse';

    }
    if(this.qnProgress==13){
      this.matSnackBar.open('You have completed mental wellness questions...!!! "The past is behind, learn from it. The future is ahead, prepare for it. The present is here, live it."', '', {
        duration: 4000,
        // horizontalPosition: 'center',
        verticalPosition: 'top',
        panelClass: 'blue-snackbar'
      });
      this.imgorder='row';
    }
    if(sessionStorage.getItem('selectedOptions')!=null){
      this.selectedStorage=JSON.parse(sessionStorage.getItem('selectedOptions'));
      this.selected=this.selectedStorage.map(e=>e[1]);
      this.selectedStorage.forEach(element => {
        if(this.qnProgress==element[0] && element[1]!=null){
          this.selectedOption=element[1];
        }
        if(this.selected.length<this.questions.length && this.qnProgress>this.selected.length-1)
        this.selectedOption=null;
       });
    }
    if(this.qnProgress==this.questions.length-1){
      this.submitQuestions=true;
    }
  }

  preQuestion(){
    if(this.qnProgress==this.questions.length-1){
      this.submitQuestions=false;
    }
    this.qnProgress--;
    if(sessionStorage.getItem('selectedOptions')!=null){
      let temp=JSON.parse(sessionStorage.getItem('selectedOptions'));
      temp.forEach(element => {
          if(this.qnProgress==element[0]){
            this.selectedOption=element[1];
          }
       });
    }
  }
  scoreBelts:any;
  canDataPost:boolean=false;
  planType:{"@id":number, "activeStatus":boolean, "chargeId":string, "emailId":string, "plan":string};
  submitQuestionsRequest(){
    this.matDialog.open(ConfirmBoxComponent, {
        width      : '300px',
        height     : '200px',
        hasBackdrop: true,
        panelClass : "confirm-dialogg",
        data       : {title:'Confirmation', message:'Are you sure, you want to submit your answers?'}
      }).afterClosed().subscribe(response=>{
        if(response==true){
      this.mapAnswered.set(this.qnProgress, this.selectedOption);
      this.planType=JSON.parse(sessionStorage.getItem('paymentResponse'));
      console.log(this.planType);
      sessionStorage.setItem('selectedOptions', JSON.stringify(Array.from(this.mapAnswered)));
      const convMap = {};
      this.mapAnswered.forEach((val: number, key: string) => {
        convMap[key] = val;
      });
      console.log(convMap);
      this._http.post(`${this.selectedAnsweredURL}?email=${this.planType.emailId}&plan=${this.planType.plan}`, convMap).subscribe(data=>{
        console.log(data);

        this.scoreBelts = data;
        this.canDataPost=true;
      });
      sessionStorage.removeItem('selectedOptions');
      this.router.navigate(['beforeDashBoard']);
      }
    });
  }
}

