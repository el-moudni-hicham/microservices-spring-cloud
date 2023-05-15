import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent {
  customers! : any;
  errMessage! : string;

  constructor(private http : HttpClient, private router : Router) {
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8890/CUSTOMER-SERVICE/customers").subscribe({
      next : data => {
        this.customers = data;
      },
      error : err => {
        this.errMessage = err;
      }
    })
  }

  getBilling(c : any){
    this.router.navigateByUrl("/customerBills/"+c.id);
  }
}
