import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-doctor-login-page',
  templateUrl: './doctor-login-page.component.html',
  styleUrls: ['./doctor-login-page.component.scss']
})
export class DoctorLoginPageComponent implements OnInit {

  email: string = "";
  password: string = "";
  doctorLoginForm = new FormGroup({});

  constructor(
    private router: Router,
    private service:HospitalService,
    private fb: FormBuilder

  ) {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
    this.doctorLoginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(pattern)]],
      password: [null, Validators.compose([Validators.required, Validators.minLength(8)])]
    });

  }

  ngOnInit(): void {
  }

  
  signIn(): void {

    const body = {
      "doctorEmailId": this.doctorLoginForm.controls['email'].value,
      "doctorPassword": this.doctorLoginForm.controls['password'].value
    }
    
    this.service.doctorSignIn(body).pipe(take(1)).subscribe((res :any) => {
      if(res && res?.doctorId){
        this.service.storeDoctorAuthorization(res?.doctorId);
        let userName = '';
        if (res?.firstName) {
          userName+=res?.firstName;
        }
        if (res?.lastName){
          userName+=' ' + res?.lastName;
        }
        this.service.storeDoctorUserName(userName);
        this.router.navigate(['/doctor/home']);
       
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong in login!!pl try again");
    })

  }

  routeToNewUser(): void {
    this.router.navigate(["/doctor-register"]);
  }

  routeToForgotPassword(): void {
    this.router.navigate(["/forgot-password"]);
  }
}
