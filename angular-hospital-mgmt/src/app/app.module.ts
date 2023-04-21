import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AboutusComponent } from './components/aboutus/aboutus.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { AppHeaderComponent } from './components/app-header/app-header.component';
import { ClientHeaderComponent } from './components/client/client-header/client-header.component';
import { AdminHeaderComponent } from './components/admin/admin-header/admin-header.component';
import { ClientHomeComponent } from './components/client/client-home/client-home.component';
import { ClientLoginPageComponent } from './components/client/client-login-page/client-login-page.component';
import { ClientSignUpComponent } from './components/client/client-sign-up/client-sign-up.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AdminLoginPageComponent } from './components/admin/admin-login-page/admin-login-page.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import { HttpClientModule } from '@angular/common/http';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatButtonModule} from '@angular/material/button';
// import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { PagingComponent } from './components/paging/paging.component';
import { DoctorLoginPageComponent } from './components/doctor/doctor-login-page/doctor-login-page.component';
import { DoctorSignUpComponent } from './components/doctor/doctor-sign-up/doctor-sign-up.component';
import { DoctorHomePageComponent } from './components/doctor/doctor-home-page/doctor-home-page.component';
import { DoctorAppHeaderComponent } from './components/doctor/doctor-app-header/doctor-app-header.component';
import { DoctorAppointmnetListComponent } from './components/doctor/doctor-appointmnet-list/doctor-appointmnet-list.component';
import { CreateAppointmentComponent } from './components/client/create-appointment/create-appointment.component';
import { MyAppointmentComponent } from './components/client/my-appointment/my-appointment.component';
import { MyPrescriptionComponent } from './components/client/my-prescription/my-prescription.component';
import { PaymentComponent } from './components/client/payment/payment.component';
import { DoctorListComponent } from './components/admin/doctor-list/doctor-list.component';
import { AdminAllAppointmentComponent } from './components/admin/admin-all-appointment/admin-all-appointment.component';
import { PaymentListComponent } from './components/admin/payment-list/payment-list.component';
import { AdminUpdateDoctorComponent } from './components/admin/admin-update-doctor/admin-update-doctor.component';



@NgModule({
  declarations: [
    AppComponent,
    AppHeaderComponent,
    AboutusComponent,
    HomePageComponent,
    ContactusComponent,
    ClientHeaderComponent,
    AdminHeaderComponent,
    ClientHomeComponent,
    ClientLoginPageComponent,
    ClientSignUpComponent,
    AdminHomeComponent,
    AdminLoginPageComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    PagingComponent,
    DoctorLoginPageComponent,
    DoctorSignUpComponent,
    DoctorHomePageComponent,
    DoctorAppHeaderComponent,
    DoctorAppointmnetListComponent,
    CreateAppointmentComponent,
    MyAppointmentComponent,
    MyPrescriptionComponent,
    PaymentComponent,
    DoctorListComponent,
    AdminAllAppointmentComponent,
    PaymentListComponent,
    AdminUpdateDoctorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatRippleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    MatMenuModule,
    MatIconModule,
    MatSnackBarModule,
    MatButtonModule
  ],
  providers: [
    DatePipe
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
