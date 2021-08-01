import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { HelplineDetailsService } from './../helpline-details.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-helpline',
  templateUrl: './helpline.component.html',
  styleUrls: ['./helpline.component.css']
})
export class HelplineComponent implements OnInit {
  cityname:string;
  helplinenumber:String;
  messages: any[] = [];
  subscription: Subscription;
  constructor(public dialog:MatDialog,private helplinedetailsService:HelplineDetailsService) {
    this.subscription = this.helplinedetailsService.getCity().subscribe(city => {
      this.cityname = city.text;
        console.log(this.cityname);
    });
    this.subscription = this.helplinedetailsService.getHelpLine().subscribe(helpline => {

      this.helplinenumber = helpline.text;

    });
   }

  ngOnInit(): void {
  }

}
