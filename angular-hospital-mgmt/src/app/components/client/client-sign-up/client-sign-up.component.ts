import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-client-sign-up',
  templateUrl: './client-sign-up.component.html',
  styleUrls: ['./client-sign-up.component.scss']
})
export class ClientSignUpComponent implements OnInit {


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
  medicalBackground: string = '';

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
    // if (this.district === '' || this.district.length < 3) {
    //   alert('District must contain atleast 3 characters');
    //   return;
    // }
    // if (this.state === '' || this.state.length < 3) {
    //   alert('State must contain atleast 3 characters');
    //   return;
    // }
    // if (this.zipcode === '' || this.zipcode.length < 6) {
    //   alert('Zipcode must contain atleast 6 characters');
    //   return;
    // }
//alert("sucess")
    const body: any = {
      firstName : this.firstName,
      lastName : this.lastName,
      contactNumber : this.phone,
      patientEmailId :this.email,
      gender :this.gender,
      patientPassword:this.password,
      age: this.age,
      address: this.address,
      bloodGroup: this.bloodGroup,
      medicalBackground: this.medicalBackground

    }
    console.log("=======>",body);
    this.service.signUp(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.patientId){
        alert("Registration sucessful");
        this.router.navigate(["/patient-login"]);
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong!!pl try again");
    })

  }

}
