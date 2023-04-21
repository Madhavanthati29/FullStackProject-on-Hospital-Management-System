import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { Appointment } from '../../model/appointment.model';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-admin-all-appointment',
  templateUrl: './admin-all-appointment.component.html',
  styleUrls: ['./admin-all-appointment.component.scss']
})
export class AdminAllAppointmentComponent implements OnInit {

   appointments: Appointment[] = [];
  constructor(
    private hService: HospitalService
  ) { 
    this.getAllAppointment();
  }

  ngOnInit(): void {
  }

  getAllAppointment(): void {
    this.hService.getAllAppointment().pipe(take(1)).subscribe((res: any) => {
      
      if (!!res && res.length > 0) {
        this.appointments = res;
        this.appointments.forEach((item: Appointment) => {
          this.hService.getPatientById(item?.patientId).pipe(take(1)).subscribe((resp: any) => {
            if (!!resp && resp?.firstName) {
              item.patientName = resp?.firstName;
            }
          })
        });
      }
    }, error => {
      alert("Error occured while fetch appointments");
    });
  }

}
