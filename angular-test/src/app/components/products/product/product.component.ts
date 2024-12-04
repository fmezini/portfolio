import { Component, Input, OnInit } from '@angular/core';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css'],
    standalone: false
})
export class ProductComponent implements OnInit {

  constructor() { }

  @Input() product: any;

  ngOnInit(): void {
  }

}
