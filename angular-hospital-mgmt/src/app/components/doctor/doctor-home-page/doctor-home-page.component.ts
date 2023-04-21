import { Component, OnInit } from '@angular/core';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-doctor-home-page',
  templateUrl: './doctor-home-page.component.html',
  styleUrls: ['./doctor-home-page.component.scss']
})
export class DoctorHomePageComponent implements OnInit {

  userName: string = '';
  constructor(
    private service: HospitalService
  ) {
    if (this.service.getDoctorName() !== null) {
      this.userName = this.service.getDoctorName();
    }
    this.service.isDoctorLoginPresent();
  }

  ngOnInit(): void {
  }

}
