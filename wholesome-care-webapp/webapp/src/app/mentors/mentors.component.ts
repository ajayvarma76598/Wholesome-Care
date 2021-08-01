import { Component, OnInit } from '@angular/core';
import dietitianData from '../../assets/mentorData/dietitians.json';
import yogaInstructorData from '../../assets/mentorData/yogaInstructors.json';
import gymInstructorData from '../../assets/mentorData/gymInstructors.json';
import { ActivatedRoute } from '@angular/router';

interface Mentor{
  id:number;
  name:String;
  image:String;
  discription:String;
}
@Component({
  selector: 'app-mentors',
  templateUrl: './mentors.component.html',
  styleUrls: ['./mentors.component.css']
})
export class MentorsComponent implements OnInit {
  selectedIndex:number;
  dietitians: Mentor[] = dietitianData;
  yogaInstructors: Mentor[] = yogaInstructorData;
  gymInstructors: Mentor[] = gymInstructorData;
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
  this.route.data.subscribe(
    (data)=>{
      this.selectedIndex = data.index;
      console.log(data.index);
    }
  );
  }

}
