import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { Payment } from '../../model/payment.model';
import { Prescription } from '../../model/prescription.model';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-my-prescription',
  templateUrl: './my-prescription.component.html',
  styleUrls: ['./my-prescription.component.scss']
})
export class MyPrescriptionComponent implements OnInit {

  clientId: any;
  prescriptionList: Array<Prescription> = [];
  paymentList: Array<Payment> = [];
  constructor(
    private service: HospitalService,
    private router: Router
  ) {
    this.service.isClientLoginPresent();
   this.clientId = this.service.getClientAuthorization();
    if (this.clientId) {
      this.getAllPrescription();
    }
  }

  ngOnInit(): void {
  }

  getAllPrescription(): void {
    this.service.getAllPrescription().pipe(take(1)).subscribe((res) => {
      if (!!res && res.length > 0) {
        this.getAllPayments();
        this.prescriptionList = res.filter((item: Prescription) => item?.patientId.toString() === this.clientId.toString()); 
        console.log('>>>>>>>', this.prescriptionList);
      }
    }, error => {
      alert("Getting error while get all prescirption");
    })
  }

  addPayment(prescription: Prescription): void {
    this.router.navigate(['/patient/payment'], {
      queryParams: {
        id: prescription?.prescriptionId
      }
    });
  }

  getAllPayments(): void {
    this.service.getAllPayments().pipe(take(1)).subscribe((res) => {
      if (!!res && res.length > 0) {
        this.prescriptionList.map((pres: Prescription) => {
          res.map((item: Payment) => {
            if (pres?.prescriptionId.toString() === item?.prescriptionId.toString() && item?.paymentStatus === "paid") {
              pres.isPaid = true;
            }
          });
        });
      }
    });
  }

}
