// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
// import { BehaviorSubject } from 'rxjs';
// import { filter } from 'rxjs/operators';
// import { TokenStorageService } from '../_services/token-storage.service';

// @Component({
//   selector: 'app-nav-bar',
//   templateUrl: './nav-bar.component.html',
//   styleUrls: ['./nav-bar.component.css']
// })
// export class NavBarComponent implements OnInit {
//   private roles: string[];
//   isLoggedIn = false;
//   showAdminBoard = false;
//   showModeratorBoard = false;
//   username: string;
//   thisUrl: BehaviorSubject<String>;
//   isHomePage:boolean = true;
//   isSamplePage=true;
//   isSentiment=true;
//   isDashboard = false;
//   constructor(private tokenStorageService: TokenStorageService, private router: Router) { }

//   ngOnInit(): void {
//     this.isLoggedIn = !this.tokenStorageService.getToken();
//     this.router.events
//     .pipe(filter((event: any) => event instanceof NavigationEnd))
//     .subscribe(event => {
//       this.thisUrl= event.url;
//       if(event.url!='/home'){
//         this.isHomePage = false;
//       }
//       if(event.url == '/sampleplan'){
//         this.isSamplePage = false;
//       }
//       if(event.url == '/analysis'){
//         this.isSentiment = false;
//       }
//       if(event.url == '/dashboard'){
//         this.isDashboard = true;
//         console.log(event.url);
//       }
//      });

//      console.log(this.isDashboard);

//     if (this.isLoggedIn) {
//       const user = this.tokenStorageService.getUser();
//       this.roles = user.roles;
//       // this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
//       // this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
//       this.username = user.displayName;

//     }
//   }

//   logout(): void {
//     this.tokenStorageService.signOut();
//     window.location.reload();
//   }
// }
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { filter } from 'rxjs/operators';
import { TokenStorageService } from '../_services/token-storage.service';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;
  thisUrl: BehaviorSubject<String>;
  isHomePage:boolean = true;
  isSamplePage=false;
  isSentiment=false;
  isDashboard = false;
  isLogIn = false;
  isProfile=false;
  isMentor=false;
role :string;
  constructor(private tokenStorageService: TokenStorageService, private router: Router) { }
  ngOnInit(): void {
    this.role = JSON.parse(sessionStorage.getItem("auth-user")).role;
    if(this.role =="ROLE_MENTOR"){
      this.isMentor = true;
    }
    this.isLoggedIn = (this.tokenStorageService.getToken()!=null || this.tokenStorageService.getToken()!=undefined);
    this.router.events
    .pipe(filter((event: any) => event instanceof NavigationEnd))
    .subscribe(event => {
      this.thisUrl= event.url;
      if(event.url=='/home'){
        this.isSamplePage = false;
        this.isSentiment = false;
        this.isHomePage = true;
        this.isDashboard = false;
        this.isLogIn = false;
  this.isProfile=false;

      }
      else if(event.url == '/sampleplan'){
        this.isSamplePage = true;
        this.isSentiment = false;
        this.isHomePage = false;
        this.isDashboard = false;
        this.isLogIn = false;
        this.isProfile=false;
      }
     else if(event.url == '/analysis'){
        this.isSamplePage = false;
        this.isSentiment = true;
        this.isHomePage = false;
        this.isDashboard = false;
        this.isLogIn = false;
        this.isProfile=false;
      }
      else if(event.url =='/dashboard'){
        this.isSamplePage = false;
        this.isSentiment = false;
        this.isHomePage = false;
        this.isDashboard = true;
        this.isLogIn = false;
        this.isProfile=false;
      }
      else if(event.url =='/userProfile'){
        this.isSamplePage = false;
        this.isSentiment = false;
        this.isHomePage = false;
        this.isDashboard = false;
        this.isLogIn = false;
        this.isProfile=true;
      }
      else if(event.url =='/login'){
        this.isSamplePage = false;
        this.isSentiment = false;
        this.isHomePage = false;
        this.isDashboard = false;
        this.isLogIn = true;
        this.isProfile=false;
      }  else if(event.url =='/'){
        this.isSamplePage = false;
        this.isSentiment = false;
        this.isHomePage = true;
        this.isDashboard = false;
        this.isLogIn = false;
        this.isProfile=false;
      }
      else{
        this.isSamplePage = false;
        this.isSentiment = false;
        this.isHomePage = false;
        this.isDashboard = false;
        this.isLogIn = false;
        this.isProfile=false;
      }
      console.log(this.isHomePage);
      console.log(this.isLogIn);
      this.isLoggedIn = (this.tokenStorageService.getToken()!=null || this.tokenStorageService.getToken()!=undefined);
     });
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      // this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.username = user.displayName;
    }
  }
  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
