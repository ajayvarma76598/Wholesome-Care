import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { EditSlotsComponent } from '../edit-slots/edit-slots.component';
import { AppointmentSlot } from '../mentor-profile/AppointmentSlot';
import { BookedAppointment } from '../mentor-profile/BookedAppointment';
import { Mentor } from '../mentor-profile/Mentor';
import { MentorServiceService } from '../_services/mentor-service.service';

@Component({
  selector: 'app-mentor-dashboard',
  templateUrl: './mentor-dashboard.component.html',
  styleUrls: ['./mentor-dashboard.component.css']
})
export class MentorDashboardComponent implements OnInit {
  mentor: Mentor;
  appointmentSlots:String[];
  bookedAppointments:BookedAppointment[];
  date:Date;
  appointmentTime:Time;
  timeDiff: Time;
  ratingArr = [0, 1, 2, 3, 4];
  email:string;

  constructor(public mentorService: MentorServiceService,public router: Router, public dialog: MatDialog, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.mentorService.getMentor(JSON.parse(sessionStorage.getItem("auth-user")).email).subscribe((data) => {
      this.mentor = data;
      this.appointmentSlots = this.mentor.appointmentSlots.map((AppointmentSlot)=>{return `${AppointmentSlot.openingTime} - ${AppointmentSlot.closingTime}`});
      this.bookedAppointments=this.mentor.bookedAppointments;
      this.date = new Date();
    });


  }


  changeTimeFormat(num:number):String{
    let endTag:String = "am";

    console.log(num);
    if(num>=24){
      num = 0;
    }else if(num>12){
      num = num-12;
      endTag = "pm";
    }
    console.log(num);
    console.log(endTag);

    let hour:String = num.toString();
      if(hour.length==1){
        hour = "0"+hour;
      }
      console.log(hour+":00"+endTag);
     return hour+":00"+endTag

  }

  changeSlotTiming(slot:String){
    let index:Number;
    index = this.appointmentSlots.indexOf(slot);
    const dialogRef = this.dialog.open(EditSlotsComponent,{
      width      : '100%',
      maxWidth   : '400px',
      height     : 'auto',
      hasBackdrop: true,
      maxHeight  : '700px',
      panelClass:'editMentorClass',
      data       : this.mentor
    });
    dialogRef.afterClosed().subscribe(result=>{
      this.mentor.appointmentSlots[index.valueOf()].openingTime = this.changeTimeFormat((Number.parseInt(result.openingTime.split(":")[0])));
      this.mentor.appointmentSlots[index.valueOf()].closingTime = this.changeTimeFormat((Number.parseInt(result.openingTime.split(":")[0])+1));
      console.log(this.mentor.appointmentSlots[index.valueOf()].closingTime);
      this.mentorService.updateMentor(this.mentor).subscribe(data=>{
        console.log(data);
      },
      error=>{
        console.log(error)
      });
    },
    error=>{

    });
  }
  addSlot(){
    let appointmentSlot:AppointmentSlot=new AppointmentSlot();
    const dialogRef = this.dialog.open(EditSlotsComponent,{
      width      : '100%',
      maxWidth   : '400px',
      height     : 'auto',
      hasBackdrop: true,
      maxHeight  : '700px',
      panelClass:'editMentorClass',
      data       : this.mentor
    });
    dialogRef.afterClosed().subscribe(result=>{
      appointmentSlot.openingTime = this.changeTimeFormat((Number.parseInt(result.openingTime.split(":")[0])));
      appointmentSlot.closingTime = this.changeTimeFormat((Number.parseInt( appointmentSlot.openingTime.split(":")[0])+1));
      this.mentor.appointmentSlots.push(appointmentSlot);
      this.mentorService.updateMentor(this.mentor).subscribe(data=>{
        console.log(data);
      },
      error=>{
        console.log(error)
      });
    },
    error=>{

    });
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
