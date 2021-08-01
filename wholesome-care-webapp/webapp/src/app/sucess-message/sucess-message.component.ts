import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

// export interface DialogData {
//   message : 'Successfull' | 'Failure' ;
// }
@Component({
  selector: 'app-sucess-message',
  templateUrl: './sucess-message.component.html',
  styleUrls: ['./sucess-message.component.css']
})
export class SucessMessageComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data: string) { }

  ngOnInit(): void {
  }

}
