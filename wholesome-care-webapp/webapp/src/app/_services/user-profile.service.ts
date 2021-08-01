import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as _ from 'lodash';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

form: any = new FormGroup({
    email:new FormControl(''),
    name: new FormControl('', Validators.required),
    age: new FormControl(''),
    place: new FormControl(''),
    creationDate: new FormControl(''),
    days: new FormControl(''),
    mentorList:new FormControl(''),
    appointments:new FormControl(''),

  });
  constructor(private http:HttpClient) { }
  API='https://wholesome-care.stackroute.io/user-service/api/v1'

public updateUserByEmail(userProfile){
  console.log(userProfile);
  return this.http.put(this.API+'/update', userProfile).subscribe((data)=>{
      });
}

 populateForm(userdata) {
    this.form.setValue(_.omit(userdata,['imageUrl','physicalLevel','mentalLevel','dietLevel','physicalScore','mentalScore','dietScore','followers','following','plan','date','planType']));
  }
}

