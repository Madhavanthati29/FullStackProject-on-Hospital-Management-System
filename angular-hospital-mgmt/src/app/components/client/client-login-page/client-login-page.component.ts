import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-client-login-page',
  templateUrl: './client-login-page.component.html',
  styleUrls: ['./client-login-page.component.scss']
})
export class ClientLoginPageComponent implements OnInit {

  email: string = "";
  password: string = "";
  clientLoginForm = new FormGroup({});

  constructor(
    private router: Router,
    private hservice:HospitalService,
    private fb: FormBuilder

  ) {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
    this.clientLoginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(pattern)]],
      password: [null, Validators.compose([Validators.required, Validators.minLength(8)])]
    });

  }

  ngOnInit(): void {
  }

  
  signIn(): void {

    const body = {
      "patientEmailId": this.clientLoginForm.controls['email'].value,
      "patientPassword": this.clientLoginForm.controls['password'].value
    }
    
    this.hservice.clientSignIn(body).pipe(take(1)).subscribe((res :any) => {
      if(res && res?.patientId){
        this.hservice.storeClientAuthorization(res?.patientId);
        let userName = '';
        if (res?.firstName) {
          userName+=res?.firstName;
        }
        if (res?.lastName){
          userName+=' ' + res?.lastName;
        }
        this.hservice.storeClientUserName(userName);
        this.router.navigate(['/patient/home']);
       
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong in login!!pl try again");
    })

  }

  routeToNewUser(): void {
    this.router.navigate(["/patient-register"]);
  }

  routeToForgotPassword(): void {
    this.router.navigate(["/forgot-password"]);
  }
}
