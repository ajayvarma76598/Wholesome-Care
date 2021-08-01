import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AppointmentSlot } from '../mentor-profile/AppointmentSlot';
import { BookedAppointment } from '../mentor-profile/BookedAppointment';
import { Mentor } from '../mentor-profile/Mentor';
import { MentorServiceService } from '../_services/mentor-service.service';

@Component({
  selector: 'app-booking-appointment',
  templateUrl: './booking-appointment.component.html',
  styleUrls: ['./booking-appointment.component.css']
})
export class BookingAppointmentComponent implements OnInit {

isMentorSelcted:boolean=false;
isTimingSelected:boolean=false;
  mentorList: Mentor[] = new Array<Mentor>();
  errMessage: String = '';
  mentorSelected: Mentor = new Mentor();
  selectedIndex: number = 0;
  appointmentSelected: AppointmentSlot = new AppointmentSlot();
  appointmentList: AppointmentSlot[] ;
  constructor(private formBuilder: FormBuilder, public mentorService: MentorServiceService, @Inject(MAT_DIALOG_DATA) public data: any) {

  }

  ngOnInit(): void {
    console.log(this.data);
    this.data.mentorList.forEach(element => {
      this.mentorService.getMentor(element.emailId).subscribe((data) => {
        this.mentorList.push(data);
      })
    });
    this.selectedIndex = 0;
    this.errMessage = '';
    console.log(this.mentorList)
  }

  selectAMentor(mentor: Mentor) {
    this.appointmentList = new Array();
    const bookedAppointments: BookedAppointment[] = mentor.bookedAppointments
      .filter(appointment => appointment.customerEmail == this.data.email);
      console.log(bookedAppointments);
    if (bookedAppointments.length > 0) {
      this.errMessage = "You already Have Appointment with " + mentor.name + " at " + bookedAppointments[0].appointmentSlot.openingTime;
    }
    else {
      this.isMentorSelcted = true;
      this.mentorSelected = mentor;
      this.selectedIndex = this.selectedIndex + 1;
      this.mentorSelected.appointmentSlots.forEach(element => {
        this.appointmentList.push(element);
      })
      console.log(this.appointmentList);
      if(this.appointmentList.filter(slot=> slot.isbooked==false).length==0){
        this.errMessage= "No Appointment Slots available for "+mentor.name;
        this.isMentorSelcted = false;
      }
    }
  }
  selectATime(slot: AppointmentSlot) {
    this.selectedIndex = this.selectedIndex+1;
    this.isTimingSelected = true;
    let appointment : BookedAppointment = new BookedAppointment();
    appointment.appointmentSlot.openingTime = slot.openingTime;
    appointment.appointmentSlot.closingTime = slot.closingTime;
    appointment.appointmentSlot.isbooked = true;
    appointment.customerEmail = this.data.email;
    appointment.customerName = this.data.name;
    this.mentorSelected.bookedAppointments.push(appointment);
    this.mentorSelected.appointmentSlots.filter(timing => timing.openingTime==slot.openingTime)[0].isbooked = true;


  }

  retry(){
    this.errMessage = '';
  }
  back(){
    this.errMessage = '';
  this.selectedIndex = this.selectedIndex-1;
  }

}
