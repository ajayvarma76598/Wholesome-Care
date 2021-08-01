import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AppConstants } from '../common/app.constants';
import { AuthService } from '../_services/auth.service';
import { RegisterCommonService } from '../_services/register-common.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent implements OnInit {

  register = new FormGroup({
    email: new FormControl('', [Validators.required]),
    age: new FormControl('',[Validators.required]),
    place: new FormControl('',[Validators.required]),
    imageUrl: new FormControl('',[Validators.required]),
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    role: new FormControl('', Validators.required),
    gender: new FormControl('', Validators.required),

    password:new FormControl ('', [Validators.required, Validators.minLength(6)])

  });

  constructor(private matSnackBar:MatSnackBar, private _http: HttpClient,private userService1: RegisterCommonService,private authService: AuthService, private tokenStorage: TokenStorageService, private route: ActivatedRoute, private userService: UserService,private router:Router) { }
  loading = false;
  submitted = false;
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
    ngAfterViewInit():void{
      r();
    }

  onClick(){

  this.userService1.registerUser(this.register.value).subscribe(
  (data)=>{
    this.matSnackBar.open(`You have successfully registered`, '', {
      duration: 2000,
      verticalPosition: 'top',
      panelClass: ['blue-snackbar']
    });
    this.register.reset();


  // this.router.navigate(['/login']);
  },

  )

  }
  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;
  googleURL = AppConstants.GOOGLE_AUTH_URL;
  loginForm: FormGroup;
  userExits:any;





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

  // login(user): void {
	// this.tokenStorage.saveUser(user);
	// this.isLoginFailed = false;
	// this.isLoggedIn = true;
	// this.currentUser = this.tokenStorage.getUser();
  // if(user.role == "ROLE_USER")
  // this.router.navigateByUrl('api/user', {skipLocationChange: false}).then(() =>
  //         this.router.navigate(["api/user"]));
  // //  this.router.navigate(['api/user']);
  //  else
  //  this.router.navigateByUrl('mentorDashboard', {skipLocationChange: false}).then(() =>
  //         this.router.navigate(["mentorDashboard"]));
  // //  this.router.navigate(['api/mentor']);



  // }
  login(user) {
    this.tokenStorage.saveUser(user);
    this.isLoginFailed = false;
    this.isLoggedIn = true;
    this.currentUser = this.tokenStorage.getUser();

    this._http.get(`https://wholesome-care.stackroute.io/user-service/api/v1/find?email=${user.email}`).subscribe(data=>{
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

}






// window.onload=function(){
// const signUpButton = document.getElementById('signUp');
// const signInButton = document.getElementById('signIn');
// const container = document.getElementById('container');
// console.log(signUpButton);
// console.log(signInButton);
// signUpButton.addEventListener('click', () => {
// 	container.classList.add("right-panel-active");
// });

// signInButton.addEventListener('click', () => {
// 	container.classList.remove("right-panel-active");
// });
// }
function r(){
  const signUpButton = document.getElementById('signUp');
  const signInButton = document.getElementById('signIn');
  const container = document.getElementById('container');
  console.log(signUpButton);
  console.log(signInButton);
  signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
  });

  signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
  });}
