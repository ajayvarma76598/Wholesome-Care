import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from './_services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class RoutingGaurdGuard implements CanActivate {
  constructor(public router:Router,private tokenStorage:TokenStorageService) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(sessionStorage.getItem("auth-token")==null){
      console.log(this.tokenStorage.getToken);
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
  
}
