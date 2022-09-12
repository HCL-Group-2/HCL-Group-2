import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from '../model/Product';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  form: FormGroup = new FormGroup([]);

  product !: Product;
  @ViewChild('id')
  input!: ElementRef<HTMLInputElement>;

  constructor(private route: ActivatedRoute,
    private router: Router, private productService: ProductService , private fb: FormBuilder) {}

  ngOnInit(): void {
    this.router.navigate(['/admin']);
    this.form = this.fb.group({
      id:[null, [Validators.required]],
      name:[null, [Validators.required]],
      description:[null, [Validators.required]],
      price:[null, [Validators.required]],
      image:[null, [Validators.required]],
      category:[null, [Validators.required]],
      inventory:[null, [Validators.required]]
    });
  }

  saveProduct(product: Product){
    this.productService.saveProduct(this.form.value).subscribe();
  }

  deleteProduct(product: Product){
    this.productService.deleteProduct(this.form.value).subscribe();
  }

}