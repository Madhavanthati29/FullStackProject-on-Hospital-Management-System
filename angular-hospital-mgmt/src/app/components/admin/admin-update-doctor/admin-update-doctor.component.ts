import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Doctor } from '../../model/doctor.modal';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-admin-update-doctor',
  templateUrl: './admin-update-doctor.component.html',
  styleUrls: ['./admin-update-doctor.component.scss']
})
export class AdminUpdateDoctorComponent implements OnInit {

  doctorId: any;
  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  phone: string = "";
  gender: string = "male";
  age: string = '';
  address: string = '';
  specialization: string = '';

  constructor(
    private activatedRouter: ActivatedRoute,
    private service: HospitalService,
    private router: Router
  ) {
    this.activatedRouter.queryParams.subscribe((res: any) => {
      if (!!res && res?.id) {
        this.doctorId = res?.id;
        this.getDoctorInfoById(this.doctorId);
      }
    });
  }

  getDoctorInfoById(id: any): void {
    this.service.getDoctorById(id).pipe(take(1)).subscribe((res: Doctor) => {
      if (!!res && res?.doctorId) {
        this.firstName = res?.firstName;
        this.lastName= res?.lastName;
        this.email = res?.doctorEmailId;
        this.phone = res?.contactNumber;
        this.gender = res?.gender;
        this.age = res?.age.toString();
        this.address = res?.address;
        this.specialization = res?.specialization;
      }
    });
  }

  ngOnInit(): void {
  }

  onUpdate(): void {
    if (this.firstName === '' || this.firstName.length < 3) {
      alert('FirstName must contain atleast 3 characters');
      return;
    }
    if (this.lastName === '' || this.lastName.length < 3) {
      alert('LastName must contain atleast 3 characters');
      return;
    }

    if (this.phone === '' || this.phone.length < 10 || this.phone.length > 10) {
      alert('Phone must contain atleast 10 characters');
      return;
    }
    const body: any = {
      firstName : this.firstName,
      lastName : this.lastName,
      contactNumber : this.phone,
      doctorEmailId :this.email,
      gender :this.gender,
      age: this.age,
      address: this.address,
      specialization: this.specialization
    }

    this.service.updateDoctorById(this.doctorId, body).pipe(take(1)).subscribe((res => {
      if (!!res && res?.doctorId) {
        alert("Doctor Updated Successfully")
        this.router.navigate(['/admin/doctor-list']);
      }
    }));
  }

}
