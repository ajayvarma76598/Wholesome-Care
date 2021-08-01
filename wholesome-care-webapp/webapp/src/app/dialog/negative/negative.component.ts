import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-negative',
  templateUrl: './negative.component.html',
  styleUrls: ['./negative.component.css']
})
export class NegativeComponent implements OnInit {

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
  }
  openDialog() {
    this.dialog.open(NegativeComponent);
  }
}