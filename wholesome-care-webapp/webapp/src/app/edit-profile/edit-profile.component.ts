import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Mentor } from '../mentor-profile/Mentor';
import { MentorServiceService } from '../_services/mentor-service.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  mentorDetails:FormGroup;
  updatedMentor:Mentor;

  constructor(public dialogRef: MatDialogRef<EditProfileComponent>, private formBuilder: FormBuilder, public mentorService: MentorServiceService, @Inject(MAT_DIALOG_DATA) public data: Mentor) { 
    this.mentorDetails = this.formBuilder.group({
      name:new FormControl('',[Validators.required]),
      expertize:new FormControl('',[Validators.required]),
      about:new FormControl('',),
      education:new FormControl('',),
      certification:new FormControl(''),
      training:new FormControl(''),
      experience:new FormControl(''),
      language:new FormControl(''),
      specialities:new FormControl('')
    })
  }

  ngOnInit(): void {
    this.mentorDetails.setValue({
      name : this.data.name ,
      expertize:this.data.expertize,
      about:this.data.about,
      education:this.data.education,
      certification:this.data.certification,
      training:this.data.training,
      experience:this.data.experience,
      language:this.data.language,
      specialities:this.data.specialities
    })
  }
}
