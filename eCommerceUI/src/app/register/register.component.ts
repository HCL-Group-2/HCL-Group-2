import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../model/User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user !: User;

  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.saveUser({id: 1, firstName: 'Front', lastName: 'End', email: 'test@gmail.com', password: 'password'})
  }

  saveUser(user: User){
    this.userService.saveUser(user).subscribe();
  }

}
