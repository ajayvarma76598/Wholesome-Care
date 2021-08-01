import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserProfileService } from '../_services/user-profile.service';
import { UserComponent } from '../dialog/user/user.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { ListComponent } from '../list/list.component';
import { BookingAppointmentComponent } from '../booking-appointment/booking-appointment.component';
import { Mentor } from '../mentor-profile/Mentor';
import { MentorServiceService } from '../_services/mentor-service.service';
import { UserAppointment } from '../model/UserAppointment';
import { BookedAppointment } from '../mentor-profile/BookedAppointment';
import { AppointmentSlot } from '../mentor-profile/AppointmentSlot';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(private _http: HttpClient,private mentorService:MentorServiceService, private matSnackBar: MatSnackBar, private matDialog: MatDialog, private mentorservice: MentorServiceService, private router: Router, public dialog: MatDialog, public service: UserProfileService) { }

  users: User[];


   datasets = [
    {
      data: [1, 1, 1, 1, 1],
      backgroundColor:['red','yellow','blue','orange','green'] ,
      label: 'My dataset',
      borderWidth: 0
    }
  ];

   labels = [];
   options = {
    cutoutPercentage: 80,
    tooltips: {
      enabled: false
    }
  };
  appointments: UserAppointment[] = new Array();
  date: Date;

  URL="https://wholesome-care.stackroute.io/user-service/api/v1/userProfile?email=";
  // beltColors=['yellow','orange','violet','brown','black'];
  data: Array<String> = [];
  userDetail;
  todo: Array<any> = [];
  done: Array<any> = [];
  followers: Array<any>;
  following: Array<any>;
  followersLength: number;
  followingLength: number;
  pastActivities: Array<any>;
  days;
  lastDayIndex:Number;
  followerMap = new Map();
  imageUrl;
  mentorList: Mentor[] = new Array();

  userName: { displayName: string, email: string, role: string } = JSON.parse(sessionStorage.getItem("auth-user"));
  name;
  ngOnInit(): void {
    if (sessionStorage.getItem('followSnackBar') != null) {
      this.matSnackBar.open(`You have successfully followed ${sessionStorage.getItem('followSnackBar')}`, '', {
        duration: 2000,
        verticalPosition: 'top',
        panelClass: ['blue-snackbar']
      });
      sessionStorage.removeItem('followSnackBar');
    }
    if (sessionStorage.getItem('unFollowSnackBar') != null) {
      this.matSnackBar.open(`You have successfully unfollowed ${sessionStorage.getItem('unFollowSnackBar')}`, '', {
        duration: 2000,
        verticalPosition: 'top',
        panelClass: ['blue-snackbar']
      });
      sessionStorage.removeItem('unFollowSnackBar');
    }

    this._http.get(`https://wholesome-care.stackroute.io/user-service/api/v1/userProfile?email=${this.userName.email}`).subscribe(data=>{
  
      this.userDetail = data;
      this.userDetail.mentorList.forEach(element => {
        this.mentorService.getMentor(element.emailId).subscribe((data) => {
          this.mentorList.push(data);
        })
      });
      console.log(this.userDetail);
      this.name=this.userDetail.name;
      this.imageUrl=this.userDetail.imageUrl;
   
      this.days = this.userDetail.days;
      this.lastDayIndex = this.userDetail.days.length-1;
      this.date = new Date();
      this.pastActivities = this.userDetail.days;
      console.log(this.pastActivities);

      if (this.userDetail.appointments != undefined) {
        this.userDetail.appointments.forEach(element => {
          this.appointments.push(element);
        });
      }
    
      this._http.get<Array<any>>(`https://wholesome-care.stackroute.io/graph-query-service/api/v1/followers?email=${this.userName.email}`).subscribe(data => {
        this.followers = data;
        this.followersLength = this.followers.length;
      });
      this._http.get<Array<any>>(`https://wholesome-care.stackroute.io/graph-query-service/api/v1/following?email=${this.userName.email}`).subscribe(data => {
        this.following = data;
        this.followingLength = this.following.length;
      });
    });
  }
  onEdit(row) {
    this.service.populateForm(row);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "35%";
    this.dialog.open(UserComponent, dialogConfig).afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.ngOnInit();
    });
  }
  openListFollowers(){
    this.matDialog.open(ListComponent, {
      width      : '70%',
      height     : '80vh',
      hasBackdrop: true,
      maxHeight  : '700px',
      data       : {users: this.followers, userType: 'followers', email:this.userDetail.email},
      panelClass: ['dialogBoxPlan']
    }).afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.ngOnInit();
    });
  }
  openListFollowing(){
    this.matDialog.open(ListComponent, {
      width      : '70%',
      height     : '80vh',
      hasBackdrop: true,
      maxHeight  : '700px',
      data       : {users: this.following, userType: 'following', email:this.userDetail.email},
      panelClass: ['dialogBoxPlan']
    }).afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.ngOnInit();
    });
  }

  bookingAppointment() {
    const dialogRef = this.matDialog.open(BookingAppointmentComponent, {
      width: '90%',
      maxWidth: "600px",
      height: '80vh',
      hasBackdrop: true,
      maxHeight: '700px',
      data: this.userDetail
    });
    dialogRef.afterClosed().subscribe(result => {
      let selectedMentor: Mentor = result;
      console.log(selectedMentor);
     
      if (this.userDetail.mentorList.filter(mentor => mentor.emailId == selectedMentor.emailId).length > 0) {
        let appointment: UserAppointment = new UserAppointment();

        const booking: AppointmentSlot = selectedMentor.bookedAppointments.filter(slot => slot.customerEmail = this.userDetail.email)[0].appointmentSlot;
        appointment.appointment.openingTime = booking.openingTime;
        appointment.appointment.closingTime = booking.closingTime;
        appointment.appointment.isbooked = true;
        appointment.mentorEmail = selectedMentor.emailId;
        appointment.mentorExpertize = selectedMentor.expertize;
        appointment.mentorName = selectedMentor.name;
        this.appointments.push(appointment);
        console.log("appointments" + this.appointments);
        this.userDetail.appointments = this.appointments;
        this.mentorservice.updateMentor(selectedMentor).subscribe((data) => {
          console.log(data);
        });
        console.log("ok" + this.userDetail);
        this._http.put(`https://wholesome-care.stackroute.io/user-service/api/v1/updateAppointment`, this.userDetail).subscribe((data) => {
          console.log("ok" + data);
        });
      }
    });

  }
  toProfile(mentor:Mentor){
    console.log(mentor);
    this.router.navigate(['/mentorProfile'],{queryParams:{data: JSON.stringify( mentor.emailId)}});
  }
}
