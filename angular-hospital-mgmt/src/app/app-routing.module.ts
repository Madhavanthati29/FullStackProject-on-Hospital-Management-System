import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { AdminAllAppointmentComponent } from './components/admin/admin-all-appointment/admin-all-appointment.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AdminLoginPageComponent } from './components/admin/admin-login-page/admin-login-page.component';
import { AdminUpdateDoctorComponent } from './components/admin/admin-update-doctor/admin-update-doctor.component';
import { DoctorListComponent } from './components/admin/doctor-list/doctor-list.component';
import { PaymentListComponent } from './components/admin/payment-list/payment-list.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

import { ClientHomeComponent } from './components/client/client-home/client-home.component';
import { ClientLoginPageComponent } from './components/client/client-login-page/client-login-page.component';
import { ClientSignUpComponent } from './components/client/client-sign-up/client-sign-up.component';
import { CreateAppointmentComponent } from './components/client/create-appointment/create-appointment.component';
import { MyAppointmentComponent } from './components/client/my-appointment/my-appointment.component';
import { MyPrescriptionComponent } from './components/client/my-prescription/my-prescription.component';
import { PaymentComponent } from './components/client/payment/payment.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { DoctorAppointmnetListComponent } from './components/doctor/doctor-appointmnet-list/doctor-appointmnet-list.component';
import { DoctorHomePageComponent } from './components/doctor/doctor-home-page/doctor-home-page.component';
import { DoctorLoginPageComponent } from './components/doctor/doctor-login-page/doctor-login-page.component';
import { DoctorSignUpComponent } from './components/doctor/doctor-sign-up/doctor-sign-up.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomePageComponent } from './components/home-page/home-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'contact-us', component: ContactusComponent },
  { path: 'about-us', component: AboutusComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'patient-register', component: ClientSignUpComponent },
  { path: 'patient-login', component: ClientLoginPageComponent },
  { path: 'doctor-register', component: DoctorSignUpComponent },
  { path: 'doctor-login', component: DoctorLoginPageComponent },
  { path: 'admin-login', component: AdminLoginPageComponent },
  {
    path: 'patient', children: [
      { path: 'home', component: ClientHomeComponent },
      { path: 'create-appointment', component: CreateAppointmentComponent },
      {
        path: 'my-appointment', component: MyAppointmentComponent
      },
      {
        path: 'my-prescription', component: MyPrescriptionComponent
      },
      {
        path: 'payment', component: PaymentComponent
      }
    ]
  },
  {
    path: 'admin', children: [
      { path: 'home', component: AdminHomeComponent },
      { path: 'doctor-list', component: DoctorListComponent },
      { path: 'all-appointment', component: AdminAllAppointmentComponent },
      { path: 'payment-list', component: PaymentListComponent },
      { path: 'update-doctor', component: AdminUpdateDoctorComponent }
    ]
  },
  {
    path: 'doctor', children: [
      { path: 'home', component: DoctorHomePageComponent },
      { path: 'appointment', component: DoctorAppointmnetListComponent }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
