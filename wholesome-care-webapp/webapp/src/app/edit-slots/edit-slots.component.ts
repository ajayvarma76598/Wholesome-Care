import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { AppointmentSlot } from '../mentor-profile/AppointmentSlot';
import { MentorServiceService } from '../_services/mentor-service.service';

@Component({
  selector: 'app-edit-slots',
  templateUrl: './edit-slots.component.html',
  styleUrls: ['./edit-slots.component.css']
})
export class EditSlotsComponent implements OnInit {

  slotDetails:FormGroup;
  constructor(public dialogRef: MatDialogRef<EditSlotsComponent>, private formBuilder: FormBuilder,  @Inject(MAT_DIALOG_DATA) public data: AppointmentSlot) { 
    this.slotDetails = this.formBuilder.group({
      openingTime: new FormControl('',Validators.required)
    });
  }

  ngOnInit(): void {
    console.log(this.data.openingTime)
    this.slotDetails.setValue({
  
    })
  }

}
