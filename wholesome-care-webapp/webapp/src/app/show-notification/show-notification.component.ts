import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-show-notification',
  templateUrl: './show-notification.component.html',
  styleUrls: ['./show-notification.component.css']
})
export class ShowNotificationComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data:{name:string, activitiesDoneList:Array<any>}) { }
  message;
  doneTasks:Array<any>;
  name:string;
  ngOnInit(): void {

      this.name=this.data.name;
      console.log(this.data.name);
      this.doneTasks=this.data.activitiesDoneList;
      console.log(this.doneTasks);
    // }
  }
}
