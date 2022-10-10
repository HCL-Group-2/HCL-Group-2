import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../model/Product';
import { ProductFeedback } from '../model/ProductFeedback';
import { ProductService } from '../product.service';
import { Observable, filter, map } from 'rxjs';
import { AuthState } from '@okta/okta-auth-js';
import { ProductFeedbackService } from '../productfeedback.service';

@Component({
  selector: 'app-feedback-form2',
  templateUrl: './feedback-form2.component.html',
  styleUrls: ['./feedback-form2.component.css']
})
export class FeedbackForm2Component implements OnInit {

  feedbackForm!: FormGroup; 

  products !: Array<Product>;
  productfeedback !: ProductFeedback;

  Products: FormGroup = new FormGroup([]);
  public Q1Form: FormGroup = new FormGroup([]);
  public Q2Form: FormGroup = new FormGroup([]);
  public Q3Form: FormGroup = new FormGroup([]);
  public Q4Form: FormGroup = new FormGroup([]);
  public Q5Form: FormGroup = new FormGroup([]);

  constructor(private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private productService: ProductService,
    private productfeedbackservice : ProductFeedbackService
  ) {}

  ngOnInit(): void {

    this.getProducts();

    this.Products = this.formBuilder.group({
      prod: ['', [Validators.required]]
    });

    this.Q1Form = this.formBuilder.group({
      q1: ['', [Validators.required]]
    });

    this.Q2Form = this.formBuilder.group({
      q2: ['', [Validators.required]]
    });

    this.Q3Form = this.formBuilder.group({
      q3: ['', [Validators.required]]
    });

    this.Q4Form = this.formBuilder.group({
      q4: ['', [Validators.required]]
    });

    this.Q5Form = this.formBuilder.group({
      q5: ['', [Validators.required]]
    }); 

  }

  getProducts() {
    this.productService.getProducts().subscribe(data => {
      this.products = data;
    });
  }

  clearForm() {
    this.Products.reset();
    this.Q1Form.reset(); 
    this.Q2Form.reset();
    this.Q3Form.reset();
    this.Q4Form.reset();
    this.Q5Form.reset();
  }

  submitFeedbackForm() {
    let product_name = this.Products.get('prod')?.value;
    console.log('product_name ' + product_name);
    let question1 = this.Q1Form.get('q1')?.value;
    console.log(this.Q1Form.value);
    let question2 = this.Q2Form.get('q2')?.value;
    console.log(this.Q2Form.value);
    let question3 = this.Q3Form.get('q3')?.value;
    console.log(this.Q3Form.value);
    let question4 = this.Q4Form.get('q4')?.value;
    console.log(this.Q5Form.value);
    let question5 = this.Q5Form.get('q5')?.value;
    console.log(this.Q5Form.value);

    this.productfeedback = { product_name: product_name, question1: question1, question2: question2, question3: question3, question4: question4, question5: question5 };
    this.productfeedbackservice.addProductFeedback(this.productfeedback).subscribe();

  }    
}