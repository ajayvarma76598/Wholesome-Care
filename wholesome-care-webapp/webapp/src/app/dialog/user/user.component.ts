import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { UserProfileService } from 'src/app/_services/user-profile.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {


  constructor(

    public dialogRef: MatDialogRef<UserComponent>,public service:UserProfileService) { }
  ngOnInit(): void {
 
  }

  onSubmit() {
      this.service.updateUserByEmail(this.service.form.value);
  this.onClose();
      
      // this.service.form.reset();
      // this.service.initializeFormGroup();
      // this.notificationService.success(':: Submitted successfully');
 
    
  }

  onClose() {
    this.service.form.reset();
    // this.service.initializeFormGroup();
    this.dialogRef.close();
  }

}