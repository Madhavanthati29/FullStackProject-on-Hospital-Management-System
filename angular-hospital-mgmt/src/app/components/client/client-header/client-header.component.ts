import { Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-client-header',
  templateUrl: './client-header.component.html',
  styleUrls: ['./client-header.component.scss']
})
export class ClientHeaderComponent implements OnInit {
  url: string = "/patien/home";
  userName: string = '';
  constructor(
    private service: HospitalService,
    private router:Router
  ) {
    if (this.service.getClientName() !== null) {
      this.userName = this.service.getClientName();
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
    if (link === '/patient/logout') {
      this.service.clientLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
