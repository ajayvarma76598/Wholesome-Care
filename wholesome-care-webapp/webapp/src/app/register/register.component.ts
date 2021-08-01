import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AbstractControl } from '@angular/forms';
import { FormControl, Validators, MinLengthValidator, FormBuilder, FormGroup, ValidatorFn, ValidationErrors } from '@angular/forms';
import { RegisterCommonService } from '../_services/register-common.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  register = new FormGroup({
    email: new FormControl('', [Validators.required]),
    age: new FormControl('',[Validators.required]),
    place: new FormControl('',[Validators.required]),
    imageUrl: new FormControl('',[Validators.required]),
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    role: new FormControl('', Validators.required),
    password:new FormControl ('', [Validators.required, Validators.minLength(5)])

  });
  constructor(private userService: RegisterCommonService,private router: Router,) { }
  loading = false;
  submitted = false;
  ngOnInit(): void {
  }
 
  onSubmit(){
 
  this.userService.registerUser(this.register.value).subscribe(
  (data)=>{
  this.router.navigate(['/login']);
  },

  )

  }
}
