import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../model/User';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']

})
export class RegisterComponent implements OnInit {
  form: FormGroup = new FormGroup([]);

  user !: User;
  @ViewChild('firstName')
  input!: ElementRef<HTMLInputElement>;

  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService , private fb: FormBuilder) {}

  ngOnInit(): void {
    //this.saveUser({firstName: 'Front', lastName: 'End', email: 'newTest@gmail.com', password: 'password'})
    this.form = this.fb.group({
      firstName:[null, [Validators.required]],
      lastName:[null, [Validators.required]],
      email:[null, [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}$")]],
      password:[null, [Validators.required]]
    });
  }


  saveUser(user: User){
    this.userService.saveUser(this.form.value).subscribe();
    this.router.navigate(['/']);
  }

}
