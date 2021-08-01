import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';
@Component({
  selector: 'app-before-dashboard',
  templateUrl: './before-dashboard.component.html',
  styleUrls: ['./before-dashboard.component.css']
})
export class BeforeDashboardComponent implements OnInit {
  userName:{displayName:string, email:string, role:string}=JSON.parse(sessionStorage.getItem("auth-user"));
  constructor(private _http:HttpClient, private router:Router) { }
  canRedirectToDashBoard:boolean=false;
  activated:string=null;
  ngOnInit(): void {
    if(sessionStorage.getItem('activated')!=null){
      this.activated=sessionStorage.getItem('activated');
    }
  }
  isStop:string='notStart';
  status:boolean=false;
  activateYourPlan(){
    this.isStop='start';
    this._http.get(`https://wholesome-care.stackroute.io/graph-query-service/api/v1/email?email=${this.userName.email}`).subscribe(data=>{
      console.log(data);
      this.status=true;
      this.isStop='notStart';
      this.canRedirectToDashBoard=true;
    });
    err=>{
      console.log(err);
    }
  }
  redirectToDashBoard(){
    this.router.navigate(['dashboard']);
  }
}
