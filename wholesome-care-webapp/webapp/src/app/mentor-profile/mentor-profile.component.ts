import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { MentorServiceService } from '../_services/mentor-service.service';
import { Mentor } from './Mentor';

@Component({
  selector: 'app-mentor-profile',
  templateUrl: './mentor-profile.component.html',
  styleUrls: ['./mentor-profile.component.css']
})
export class MentorProfileComponent implements OnInit {

  mentor:Mentor;
  ratingArr=[0,1,2,3,4];
  role:string;
  isMentor:boolean = false;
  constructor(public mentorService:MentorServiceService, public dialog: MatDialog,public router:Router,private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe((params)=>{

      this.mentorService.getMentor(params.data.split("\"")[1]).subscribe((data)=>{
        this.mentor=data;
        console.log(this.mentor);
      });
    })
    this.role = JSON.parse(sessionStorage.getItem("auth-user")).role;
    if(this.role =="ROLE_MENTOR"){
      this.isMentor = true;
    }
  }
  showIcon(index:number) {
    if (this.mentor.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }
  editDashboard(){
    if(this.isMentor){
      this.router.navigate(['/mentorDashboard']);
    }
    else{
      this.router.navigate(['/userProfile'])
    }
  }
  editProfile(){
    const dialogRef = this.dialog.open(EditProfileComponent,{
      width      : '100%',
      maxWidth   : '400px',
      height     : 'auto',
      hasBackdrop: true,
      maxHeight  : '700px',
      panelClass:'editMentorClass',
      data       : this.mentor
    });

    dialogRef.afterClosed().subscribe(result=>{
      this.mentor.name = result.name;
      this.mentor.expertize = result.expertize;
      this.mentor.about = result.about;
      this.mentor.education = result.education;
      this.mentor.certification = result.certification;
      this.mentor.training = result.training;
      this.mentor.experience = result.experience;
      this.mentor.language = result.language;
      this.mentor.specialities = result.specialities
      this.mentorService.updateMentor(this.mentor).subscribe(data=>{
        console.log(data);
      },
      error=>{
        console.log(error)
      });
    })

  }

}
