import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../model/Address';
import { User } from '../model/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  form: FormGroup = new FormGroup([]);

  user !: User;
  address !: Address;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      //id: "1",
      address1:[null, [Validators.required]],
      address2:[null],
      city:[null, [Validators.required]],
      state:[null, [Validators.required]],
      zipcode:[null, [Validators.required]],
      //user_id: this.user.id,
    });
  }

  saveAddress(address: Address){
    // this.addressService.saveAddress(this.form.value).subscribe();
    console.log("Address Saved");
    console.log(this.form.controls['address1'].value);
    var user_id = 1;
  }

}
