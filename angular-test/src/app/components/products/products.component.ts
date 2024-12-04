import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.css'],
    standalone: false
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

  handleEmitter(event: any) {
    const index = this.products.indexOf(event);
    this.products.splice(index, 1);
    alert(`${event.name} has been deleted`);
  }

  ngOnInit(): void {
  }

}
