import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-product-reactive',
  imports: [ReactiveFormsModule],
  templateUrl: './add-product-reactive.component.html',
  styleUrl: './add-product-reactive.component.css'
})
export class AddProductReactiveComponent {

  // using form group and form controls
  controls = new FormGroup({
    id: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    description: new FormControl('')
  });

  addProduct() {
    alert(this.controls.value['id'] + ' ' + this.controls.value['name'] + ' ' + this.controls.value['description']);
  }


}
