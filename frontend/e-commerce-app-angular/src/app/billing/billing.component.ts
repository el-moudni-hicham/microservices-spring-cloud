import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.css']
})
export class BillingComponent {
  billing! : any;
  errMessage! : string;

  customerId! : number;

  constructor(private http : HttpClient, private router : Router, private route : ActivatedRoute) {
    this.customerId = route.snapshot.params['customerId'];
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8890/BILLING-SERVICE/customerBills/" + this.customerId).subscribe({
      next : data => {
        this.billing = data;
      },
      error : err => {
        this.errMessage = err;
      }
    })
  }

}
