import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-product',
  imports: [FormsModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {

  product = {
    id: '',
    name: '',
    description: ''
  }

  product2 = {
    id: '',
    name: '',
    description: ''
  }

  addProduct() {
    alert(this.product.id + ' ' + this.product.name + ' ' + this.product.description);
  }

  addProduct2() {
    alert(this.product2.id + ' ' + this.product2.name + ' ' + this.product2.description);
  }
}
