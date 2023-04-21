import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-doctor-sign-up',
  templateUrl: './doctor-sign-up.component.html',
  styleUrls: ['./doctor-sign-up.component.scss']
})
export class DoctorSignUpComponent implements OnInit {


  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  dob: string = "";
  phone: string = "";
  district: string = "";
  state: string = "";
  zipcode: string = "";
  gender: string = "male";
  age: string = '';
  address: string = '';
  bloodGroup: string = '';
  specialization: string = '';

  constructor(
    
    private router: Router,
    private datePipe: DatePipe,
    private service:HospitalService

  ) { }

  ngOnInit(): void {
  }
  setDOB(ev: any): void {
    const date: any = this.datePipe.transform(ev?.value, 'yyyy-MM-dd');
    this.dob = date;
  }


  signup(): void {
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
      doctorPassword:this.password,
      age: this.age,
      address: this.address,
      specialization: this.specialization
    }
   
    this.service.signUpDoctor(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.doctorId){
        alert("Registration sucessful");
        this.router.navigate(["/doctor-login"]);
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong!!pl try again");
    })

  }

}
