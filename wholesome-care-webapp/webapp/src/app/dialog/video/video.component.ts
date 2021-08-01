import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { SentinmentService } from 'src/app/_services/sentinment.service';
@Component({
  selector: 'app-video',
  templateUrl: './video.component.html',
  styleUrls: ['./video.component.css']
})
export class VideoComponent implements OnInit {

  res: any;
  video:any;
  item1:any;
  url:any
  constructor(@Inject(MAT_DIALOG_DATA) public activity:any,@Inject(MAT_DIALOG_DATA) public data:any,public service:SentinmentService,private dom:DomSanitizer) { 
    // this.videoURL=this.dom.bypassSecurityTrustResourceUrl(this.url); 
  

  }
  videoURL:any;
  ngOnInit(): void {
    console.log(this.activity);
    this.service.getResource(this.data).subscribe((videonam)=>{
      this.res=videonam;
      
      this.res.items.forEach(item=>{
        console.log(item.id.videoId);
        this.url=`https://www.youtube.com/embed/${item.id.videoId}`;
        this.videoURL=this.dom.bypassSecurityTrustResourceUrl(this.url); 

        // this.video=document.getElementById("videos").innerHTML="<iframe width=\"420\" height=\"315\" src=\"http://www.youtube.com/embed/"+item.id.videoId+"\" frameborder=\"0\" allowfullscreen></iframe>";
       this.item1=item.id.videoId;   
       console.log(this.item1);
       console.log("hello"+this.videoURL);
    })
    console.log(this.res);
  });
  }
  }
   