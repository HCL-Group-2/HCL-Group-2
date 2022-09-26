import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, FormControl, FormArray, Validators, AbstractControl } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ComplexValidators } from '../global/complex-validators';

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.css']
})
export class FeedbackFormComponent implements OnInit {

  feedbackForm!: FormGroup; // Declaring a variable of type FormGroup
  morefeedbacksControls!: FormArray;

  constructor(private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) { 
    this.buildFeedbackForm();
  }

  ngOnInit() {
  }

  buildFeedbackForm() {
    // Building the Feedback Form Group
    this.feedbackForm = this.formBuilder.group({
      morefeedbacks: this.formBuilder.array([
        this.formBuilder.control(null)
      ]) 
    }); 

    // Building the FormArray Control
    this.morefeedbacksControls = this.feedbackForm.get('morefeedbacks') as FormArray;

    };

    addMoreFeedback() {
     this.morefeedbacksControls.push(this.formBuilder.control(null));
    }

    deleteMoreFeedback(index: number) {
    this.morefeedbacksControls.removeAt(index);
    }

    clearForm() {
    this.feedbackForm.reset(); // Resets the formgroup
    }

    submitFeedbackForm() {
      console.log(this.feedbackForm.value);
    }    
  }