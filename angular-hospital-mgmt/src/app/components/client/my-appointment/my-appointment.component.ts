import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { Appointment } from '../../model/appointment.model';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-my-appointment',
  templateUrl: './my-appointment.component.html',
  styleUrls: ['./my-appointment.component.scss']
})
export class MyAppointmentComponent implements OnInit {

  customer: any = {};
   appointments: Appointment[] = [];
  constructor(
    private hService: HospitalService
  ) { 
    this.getCustomerDetail();
  }

  ngOnInit(): void {
  }

  getCustomerDetail(): void {
    const cid = this.hService.getClientAuthorization();
    this.hService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        if (!!res && res?.patientId) {
          this.customer = res;
          this.getAllAppointment(res?.patientId);
        }
      }, err => {
        console.log("Err");
      }
    )
  }

  getAllAppointment(patientId: number): void {
    this.hService.getAllAppointment().pipe(take(1)).subscribe((res: any) => {
      console.log('>>>', res, patientId);
      if (!!res && res.length > 0) {
        this.appointments = res.filter((apt: Appointment) => apt?.patientId === patientId);
      }
    }, error => {
      alert("Error occured while fetch appointments");
    });
  }

}
