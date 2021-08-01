import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { PlanViewComponent } from '../plan-view/plan-view.component';

@Component({
  selector: 'app-sample-plan',
  templateUrl: './sample-plan.component.html',
  styleUrls: ['./sample-plan.component.css']
})
export class SamplePlanComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {
  }
  openDialog() {
    const dialogRef = this.dialog.open(PlanViewComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}
