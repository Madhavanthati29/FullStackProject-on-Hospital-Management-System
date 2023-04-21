import { Component } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
import { HospitalService } from './components/service/hospital.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'grocessary-management-front-end';
  isLoggedIn: boolean = false;
  isAdminLoggedIn: boolean = false;
  isDoctorLoggedIn: boolean = false;

  constructor(
    private hervice:HospitalService,
    private router:Router
  ){
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      if (this.hervice.getClientAuthorization() !== null) {
        setTimeout(() => {
          this.isLoggedIn = true;
          this.isAdminLoggedIn = false;
          this.isDoctorLoggedIn = false;
        }, 100);
      } else if (this.hervice.getDoctorAuthorization() !== null) {
        setTimeout(() => {
          this.isDoctorLoggedIn = true;
          this.isAdminLoggedIn = false;
          this.isLoggedIn = false;
        }, 100);
      } else  {
        if (this.hervice.getAdminAuthorization() !== null) {
          setTimeout(() => {
            this.isAdminLoggedIn = true;
            this.isLoggedIn = false;
            this.isDoctorLoggedIn = false;
          }, 100);

        } {
          setTimeout(() => {
            this.isLoggedIn = false;
            this.isAdminLoggedIn = false;
            this.isDoctorLoggedIn = false;
          }, 1);
        }
      }
    });
//line 20 to 41-->check when routing(url) change it will check authorization
  }


  
}
