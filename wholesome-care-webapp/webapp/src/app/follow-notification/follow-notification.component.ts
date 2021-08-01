import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { NotificationModel } from '../dashboard/NotificationModel';
@Component({
  selector: 'app-follow-notification',
  templateUrl: './follow-notification.component.html',
  styleUrls: ['./follow-notification.component.css']
})
export class FollowNotificationComponent implements OnInit {
  constructor(private dialogRef: MatDialogRef<FollowNotificationComponent>, @Inject(MAT_DIALOG_DATA) public activity:any, private dom:DomSanitizer) { }
  // stompClient = null;
  // message;
  url;
  videoURL;
  ngOnInit(): void {
    console.log(this.activity);
    this.url=this.activity.videoUrl;
    this.videoURL=this.dom.bypassSecurityTrustResourceUrl(this.url);

    // console.log(this.data);

    // this.connect();
  }
  // notify(){
  //   if(this.data=="message"){
  //     this.stompClient.send("/ws/message", {}, "Hello");
  //   } else if(this.data=="doneTaskList"){
  //     this.stompClient.send("/ws/doneTask", {}, "Hello");
  //   }
  //   this.dialogRef.close();
  // }
  // connect() {
  //   const socket = new SockJS('https://wholesome-care.stackroute.io/socket-notification-service/our-websocket');
  //   this.stompClient = Stomp.over(socket);
  //   const _this = this;
  //   this.stompClient.connect({}, function (frame) {
  //     console.log('Connected: ' + frame);
  //  });
  // }
  // dontNotify(){
  //   this.dialogRef.close();
  // }
}
