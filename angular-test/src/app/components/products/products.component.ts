import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  constructor() { }

  products = [
    {
      id: 1,
      name: 'TV'
    },
    {
      id: 2,
      name: 'Car'
    }
  ]

  ngOnInit(): void {
  }

}
