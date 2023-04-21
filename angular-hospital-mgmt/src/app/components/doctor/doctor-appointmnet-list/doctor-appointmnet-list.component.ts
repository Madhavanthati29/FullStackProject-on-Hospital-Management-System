import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Appointment } from '../../model/appointment.model';
import { Prescription } from '../../model/prescription.model';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-doctor-appointmnet-list',
  templateUrl: './doctor-appointmnet-list.component.html',
  styleUrls: ['./doctor-appointmnet-list.component.scss']
})
export class DoctorAppointmnetListComponent implements OnInit {

  apptList: Appointment[] = [];
  doctorId: any;
  constructor(
    private service: HospitalService,
    private router: Router
  ) { 
    this.service.isDoctorLoginPresent();
    this.doctorId = this.service.getDoctorAuthorization();
  }

  ngOnInit(): void {
    this.service.getAllAppointment().pipe(take(1)).subscribe((res: any) => {
      if (!!res && Array.isArray(res) && res.length > 0) {
        this.apptList = res.filter((item: Appointment) => item?.doctor?.doctorId.toString() ===  this.doctorId.toString());
        this.apptList.forEach((item: Appointment) => {
          this.service.getPatientById(item?.patientId).pipe(take(1)).subscribe((resp: any) => {
            if (!!resp && resp?.firstName) {
              item.patientName = resp?.firstName;
            }
          })
        });

        this.getAllPrescriptionList();
      }
    }, error => {
      alert("Error getting while get all appoitment");
    });
  }

  getAllPrescriptionList(): void {
    this.service.getAllPrescription().pipe(take(1)).subscribe((res: Prescription[]) => {
      if (!!res && res.length > 0) {
        this.apptList.forEach((app: Appointment) => {
          res.forEach((item: Prescription) => {
            if (item?.bookingAppointment?.appointmentId.toString() === app?.appointmentId.toString()) {
              app.isAlreadyPrescribed = true;
            }
          });
        });
      }
    });
  }

  addPrescription(apt: Appointment): void {
    const body: any = {
      BookingAppointment: apt,
      patientId: apt?.patientId,
      doctorId: apt?.doctor?.doctorId,
      status: 'admit'
    };
    this.service.addPrescription(apt?.appointmentId, body).pipe(take(1)).subscribe((res: any) => {
      if (!!res && res?.prescriptionId) {
        alert("Prescription prescirbe successfully");
        this.router.navigate(['/doctor/home']);
      }
    }, error => {
      alert("Getting error while generate appointment.");
    })
  }

}
