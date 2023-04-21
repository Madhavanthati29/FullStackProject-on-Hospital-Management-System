import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { Payment } from '../../model/payment.model';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.scss']
})
export class PaymentListComponent implements OnInit {

  payments: Payment[] = [];
  constructor(
    private hService: HospitalService
  ) { 
    this.getAllPayment();
  }

  ngOnInit(): void {
  }

  getAllPayment(): void {
    this.hService.getAllPayments().pipe(take(1)).subscribe((res: any) => {
      if (!!res && res.length > 0) {
        this.payments = res;
      }
    }, error => {
      alert("Error occured while get Payments");
    });
  }

}
