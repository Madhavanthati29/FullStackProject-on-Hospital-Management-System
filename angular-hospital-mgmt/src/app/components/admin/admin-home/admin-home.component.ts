import { Component, OnInit } from '@angular/core';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {
  userName: string = '';
  constructor(
    private hervice: HospitalService
  ) {
    if (this.hervice.getAdminName() !== null) {
      this.userName = this.hervice.getAdminName();
    }
    this.hervice.isAdminLoginPresent();
  }

  ngOnInit(): void {
  }

}
