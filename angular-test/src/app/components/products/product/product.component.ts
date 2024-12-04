import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css'],
    standalone: false
})
export class ProductComponent implements OnInit {

  constructor() { }

  @Input() product: any;

  @Output() emitter: EventEmitter<string> = new EventEmitter<string>();

  ngOnInit(): void {
  }

  delete() {
      this.emitter.emit(this.product);
  }

}
