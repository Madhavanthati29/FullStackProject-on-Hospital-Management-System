import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Doctor } from '../../model/doctor.modal';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.scss']
})
export class DoctorListComponent implements OnInit {

  doctor: Doctor[] = [];
  constructor(
    private service: HospitalService,
    private route: Router
  ) {
    this.getAllDoctorList();
  }

  ngOnInit(): void {
  }

  getAllDoctorList(): void {
    this.service.getDoctorList().pipe(take(1)).subscribe((res) => {
      if (!!res && res.length > 0) {
        this.doctor = res;
      }
    }, err => {
      console.log('Error occured while get doctor list');
    })
  }

  onDelete(doctor: Doctor): void  {
    this.service.deleteDoctorById(doctor?.doctorId).pipe(take(1)).subscribe((res) => {
      if(!!res) {
        alert("Doctor deleted successfully");
        this.getAllDoctorList();
        this.route.navigate(['/admin/doctor-list']);
      }
    });
  }

  onUpdate(doctor: Doctor): void  {
    this.route.navigate(['/admin/update-doctor'], {
      queryParams: {
        id: doctor?.doctorId
      }
    });
  }

}
