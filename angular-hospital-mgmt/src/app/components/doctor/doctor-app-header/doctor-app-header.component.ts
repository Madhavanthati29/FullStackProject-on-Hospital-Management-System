import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-doctor-app-header',
  templateUrl: './doctor-app-header.component.html',
  styleUrls: ['./doctor-app-header.component.scss']
})
export class DoctorAppHeaderComponent implements OnInit {

  url: string = "/doctor/home";
  userName: string = '';
  constructor(
    private router: Router,
    private service :HospitalService
  ) {
    if (this.service.getDoctorName() !== null) {
      this.userName = this.service.getDoctorName();
    }
  }

  ngOnInit(): void {
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });
  }

  routerToLink(link: string): void {
    if (link === '/doctor/logout') {
      this.service.clientLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
