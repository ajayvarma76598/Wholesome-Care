import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AppConstants } from '../common/app.constants';
import { FormGroup,FormControl,Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  userExits:any;
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  googleURL = AppConstants.GOOGLE_AUTH_URL;
  loginForm: FormGroup;


  constructor(private _http: HttpClient,private authService: AuthService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private userService: UserService,private router:Router ) {}

  ngOnInit(): void {
	const token: string = this.route.snapshot.queryParamMap.get('token');
	const error: string = this.route.snapshot.queryParamMap.get('error');
  	if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
    }
  	else if(token){
  		this.tokenStorage.saveToken(token);
  		this.userService.getCurrentUser().subscribe(
  		      data => {
  		        this.login(data);
  		      },
  		      err => {
  		        this.errorMessage = err.error.message;
  		        this.isLoginFailed = true;
  		      }
  		  );
  	}
  	else if(error){
  		this.errorMessage = error;
	    this.isLoginFailed = true;
  	}

  }

   onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.login(data.user);
        console.log(data.user);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }


  login(user) {
	this.tokenStorage.saveUser(user);
	this.isLoginFailed = false;
	this.isLoggedIn = true;
	this.currentUser = this.tokenStorage.getUser();
  
  this._http.get(`https://3.108.244.156:8086/user-service/api/v1/find?email=${user.email}`).subscribe(data=>{
    this.userExits=data;
    console.log(data);
    if(this.userExits){
      console.log(this.userExits);
      this.router.navigateByUrl('dashboard', {skipLocationChange: false}).then(() =>
      this.router.navigate(["dashboard"]));
    }
    else if(user.role == "ROLE_USER")
    this.router.navigateByUrl('pricing', {skipLocationChange: false}).then(() =>
            this.router.navigate(["pricing"]));
    //  this.router.navigate(['api/user']);
     else
     this.router.navigateByUrl('mentorDashboard', {skipLocationChange: false}).then(() =>
            this.router.navigate(["mentorDashboard"]));
    //  this.router.navigate(['api/mentor']);
  });

  
}
  forgetPassword(){
    this.router.navigate(['forget']);
    }
}
