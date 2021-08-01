import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Mentor } from '../mentor-profile/Mentor';

@Component({
  selector: 'app-mentor-profile-head',
  templateUrl: './mentor-profile-head.component.html',
  styleUrls: ['./mentor-profile-head.component.css']
})
export class MentorProfileHeadComponent implements OnInit {
  
  @Input() mentor: Mentor;
  ratingArr = [0, 1, 2, 3, 4];
  email:string;

  constructor( public router: Router) { }

  ngOnInit(): void {
  }
  showIcon(index: number) {
    if (this.mentor.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }
  toProfile(){
    this.email= this.mentor.emailId;
    this.router.navigate(['/mentorProfile'],{queryParams:{data: JSON.stringify(this.email)}});
  }
}
