import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  // users:Array<{'email':string, 'name':string, 'userImageUrl':string}>[];
  users:any;
  constructor(@Inject(MAT_DIALOG_DATA) private list:any, private _http:HttpClient, private matSnackBar:MatSnackBar, private dialogRef: MatDialogRef<ListComponent>, private router:Router) {}
  followThisUser:Array<any>=[];
  userType;
  fromEmail;
  ngOnInit(): void {
    this.users = this.list.users;
    this.userType = this.list.userType;
    this.fromEmail = this.list.email;
  }
  followUser(toemail, name){
    this._http.post(`https://wholesome-care.stackroute.io/graph-query-service/api/v1/follow?fromEmail=${this.fromEmail}&toEmail=${toemail}`,null)
    .subscribe(data=>{console.log(data)});
    this.dialogRef.close();
    // sessionStorage.setItem('follow', 'followed');
    sessionStorage.setItem('followSnackBar', name);
    window.location.reload();
  }
  unfollowUser(toemail, name){
    this._http.post(`https://wholesome-care.stackroute.io/graph-query-service/api/v1/unfollow?fromEmail=${this.fromEmail}&toEmail=${toemail}`,null)
    .subscribe(data=>{console.log(data)});
    this.dialogRef.close();
    sessionStorage.setItem('unFollowSnackBar', name);
    // this.ngOnInit();
    // this.router.navigate(['dashboard']);
    // window.location.reload();
  }
}
