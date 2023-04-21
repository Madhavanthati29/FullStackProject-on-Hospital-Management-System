import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {
 logo:string="../../../assets/images/grocery.jpg";
 images = [
    '../../../assets/images/banner2.jpeg',
    '../../../assets/images/banner1.jpeg',
    '../../../assets/images/banner3a.jpg'
];

  constructor(
   
    private route: Router
  ) {
    
  }

  ngOnInit(): void {
  }

  gotoLogin(): void {
    this.route.navigate(['/client-login'])
  }

}
