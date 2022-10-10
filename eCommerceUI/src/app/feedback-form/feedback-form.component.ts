import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, FormControl, FormArray, Validators, AbstractControl } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { WebsiteFeedback } from '../model/WebsiteFeedback';
import { WebsiteFeedbackService } from '../websitefeedback.service';

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.css']
})
export class FeedbackFormComponent implements OnInit {

  feedbackForm: FormGroup = new FormGroup([]); // Declaring a variable of type FormGroup

  websitefeedback !: WebsiteFeedback;

  constructor(private route: ActivatedRoute, private router: Router, private fb: FormBuilder, private websitefeedbackservice : WebsiteFeedbackService) {}

  ngOnInit(): void {
    this.feedbackForm = this.fb.group({
      first_name:[null, [Validators.required]],
      last_name:[null, [Validators.required]],
      email:[null, [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}$")]],
      suggestions:[null, [Validators.required]]
    });
  }

    clearForm() {
    this.feedbackForm.reset(); // Resets the formgroup
    }

    submitFeedbackForm() {
    let first_name = this.feedbackForm.get('first_name')?.value;
    console.log('first_name ' + first_name);
    let last_name = this.feedbackForm.get('last_name')?.value;
    console.log('last_name ' + last_name);
    let email = this.feedbackForm.get('email')?.value;
    console.log('email ' + email);
    let suggestions = this.feedbackForm.get('suggestions')?.value;
    console.log('suggestions ' + suggestions);

    this.websitefeedback = { first_name: first_name, last_name: last_name, email: email, suggestions: suggestions };
    this.websitefeedbackservice.addWebsiteFeedback(this.websitefeedback).subscribe();
    }    
  }