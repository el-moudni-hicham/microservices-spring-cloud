import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {Product} from "../model/product.model";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {
  products! : any;
  errMessage! : string;

  constructor(private http : HttpClient) {
  }

  ngOnInit(): void {
    this.http.get("http://localhost:8890/INVENTORY-SERVICE/products").subscribe({
      next : data => {
        this.products = data;
      },
      error : err => {
        this.errMessage = err;
      }
    })
  }
}
