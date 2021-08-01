import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';
import { SentinmentService } from '../_services/sentinment.service';

@Component({
  selector: 'app-activity-content',
  templateUrl: './activity-content.component.html',
  styleUrls: ['./activity-content.component.css']
})
export class ActivityContentComponent implements OnInit {
  res: any;
  video:any;
  item1:any;
  url:any
  constructor(@Inject(MAT_DIALOG_DATA) public data:any, public service:SentinmentService, private dom:DomSanitizer) {
  }
  videoURL:any;
  ngOnInit(): void {
    this.service.getResource(this.data).subscribe((videonam)=>{
      this.res=videonam;
        this.res.items.forEach(item=>{
          this.url=`http://www.youtube.com/embed/${item.id.videoId}`;
          this.videoURL=this.dom.bypassSecurityTrustResourceUrl(this.url);
          this.item1=item.id.videoId;
        });
      });
    }
  }




