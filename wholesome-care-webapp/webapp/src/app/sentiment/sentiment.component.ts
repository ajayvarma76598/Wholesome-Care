import { HttpClient } from '@angular/common/http';

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NegativeComponent } from '../dialog/negative/negative.component';
import { NeutralComponent } from '../dialog/neutral/neutral.component';
import { PositiveComponent } from '../dialog/positive/positive.component';
import { VideoComponent } from '../dialog/video/video.component';
import { SentinmentService } from '../_services/sentinment.service';

@Component({
  selector: 'app-sentiment',
  templateUrl: './sentiment.component.html',
  styleUrls: ['./sentiment.component.css']
})
export class SentimentComponent implements OnInit {
 InputForm = new FormGroup({
    input: new FormControl('', Validators.required),
  });
 
  // data: any;
  data: Array<String> = [];
  result: any;
  res: any;
  video:any;
  res1:any;
  constructor(private service: SentinmentService,private http:HttpClient,private dialog:MatDialog) { }
  inputSentFood:any;
positive:any;
negative:any;
  ngOnInit(): void {
  }
  onSubmit(){
    this.service.getKeyword(this.InputForm.value).subscribe((data)=>{
       this.res1 = data;
       // console.log(this.res1[0])
      
       if(this.res1[0]=="Positive"){
         
       const dialogRef = this.dialog.open(PositiveComponent);
       dialogRef.afterClosed().subscribe(result => {
         this.result = data
       });
     }else if(this.res1[0] == "Negative"){
       const dialogRef = this.dialog.open(NegativeComponent);
       dialogRef.afterClosed().subscribe(result => {
         this.result = data
       });
     }
     else{
       const dialogRef = this.dialog.open(NeutralComponent);
       dialogRef.afterClosed().subscribe(result => {
         this.result = data
       });
    
     }
     });
   
   
   }

   

   onResourceSubmit(data : any){
    this.dialog.open(VideoComponent, {
      width      : '70%',
      height     : '80vh',
      hasBackdrop: true,
      maxHeight  : '700px',
      data       : data
    });
//      this.service.getResource(data).subscribe((videonam)=>{
//        this.res=videonam;
       
//        this.res.items.forEach(item=>{
//        this.video=  
 
 
 
//   document.getElementById("videos").innerHTML="<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/"+item.id.videoId+"\" frameborder=\"0\" allowfullscreen></iframe>";
           
//      })
//      console.log(this.res);
//    });
 }
 
 }
 