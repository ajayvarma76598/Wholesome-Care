import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ActivityContentComponent } from '../activity-content/activity-content.component';
import { FollowNotificationComponent } from '../follow-notification/follow-notification.component';
@Component({
  selector: 'app-activity-card',
  templateUrl: './activity-card.component.html',
  styleUrls: ['./activity-card.component.css']
})
export class ActivityCardComponent implements OnInit {
  @Input() activity:any;
  constructor(private matDialog: MatDialog) { }
  imageUrlActivity;
  ngOnInit(): void {
    this.imageUrlActivity=this.activity.imageUrl;
  }
  startActivity(activity){
    this.matDialog.open(FollowNotificationComponent,{
      width      : '70%',
      height     : '80vh',
      hasBackdrop: true,
      maxHeight  : '700px',
      data       : activity
    });
  }
}
