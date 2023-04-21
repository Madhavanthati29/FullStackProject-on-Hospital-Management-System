import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { take } from 'rxjs';
import { HospitalService } from '../../service/hospital.service';

@Component({
  selector: 'app-client-home',
  templateUrl: './client-home.component.html',
  styleUrls: ['./client-home.component.scss']
})
export class ClientHomeComponent implements OnInit {

  
  quantity: number = 0;
  customer: any = {};
  getCategoryList: any[] = [];
  category: any = 100;
 
  offset: number = 0;
  pageSize: number = 10; // How many item you want to display in your page.
  totalItem: number = 1;

  constructor(
    private hService: HospitalService,
    private router: Router,
    private snakcbar: MatSnackBar
  ) {
   this.hService.isClientLoginPresent();
    this.getCustomerDetail();
  }

  ngOnInit(): void {
   
  }

  getCustomerDetail(): void {
    const cid = this.hService.getClientAuthorization();
    this.hService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Customer*****", res);
        if (!!res && res?.customerId) {
          this.customer = res;
        }
      }, err => {
        console.log("Err");
      }
    )
  }

  // getProductList(isAllProduct: boolean = false): void {
  //   let product: any = this.gService.getAllProducts(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
  //   if (!isAllProduct) {
  //     product = this.gService.getProductByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
  //   }
  //   product.pipe(take(1)).subscribe((res: any) => {
  //     ;
  //     if (res && res?.product && Array.isArray(res?.product)) {
  //       this.productList = res?.product;
  //       this.allProductList = res?.product;
  //       this.totalItem = res?.totalProduct;
  //     }
  //   }, (err: any) => {
  //     console.log("Error");
  //   });
  // }

  

  // getProductByCategory(): void {
  //   this.offset = 0;
  //   this.totalItem = 1;
  //   if (this.category === "100") {
  //     this.getProductList(true);
  //   } else {
  //     this.getProductList(false);
  //   }
  // }

  // onNextPageClick(pageOffSet: any): void {
  //   this.offset = pageOffSet;
  //   this.getProductList(this.category === 100 || this.category === "100");
  // }

  // onPreviousPageClick(pageOffSet: any): void {
  //   this.offset -= 1;
  //   this.getProductList(this.category === 100 || this.category === "100");
  // }

  // onFirstPageClick(pageOffSet: any): void {
  //   this.offset = 0;
  //   this.getProductList(this.category === 100 || this.category === "100");
  // }

  // onLastPageClick(pageOffSet: any): void {
  //   const lastPage = Math.ceil(this.totalItem / this.pageSize);
  //   this.offset = lastPage;
  //   this.getProductList(this.category === 100 || this.category === "100");
  // }

}



