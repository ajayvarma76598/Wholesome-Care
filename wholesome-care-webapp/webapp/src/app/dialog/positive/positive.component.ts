import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-positive',
  templateUrl: './positive.component.html',
  styleUrls: ['./positive.component.css']
})
export class PositiveComponent implements OnInit {

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
  }
  openDialog() {
    this.dialog.open(PositiveComponent);
  }
}