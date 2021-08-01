import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
// import { EventEmitter } from 'events';
@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {
  @Input() suggestion:any;
  @Input() fromEmail:any;
  @Output() increaseCounterOfFollow=new EventEmitter();
  imageUrl;
  constructor(private _http:HttpClient) {}
  ngOnInit(): void {
    this.imageUrl=this.suggestion.imageUrl;
  }
  count;
  followUser(toemail, name){
    this._http.post(`https://wholesome-care.stackroute.io/graph-query-service/api/v1/follow?fromEmail=${this.fromEmail}&toEmail=${toemail}`,null)
    .subscribe(data=>{
      console.log(data);
    });
    sessionStorage.setItem('followSnackBar', name);
    this.increaseCounterOfFollow.emit(this.count);
    // window.location.reload();
  }
}
