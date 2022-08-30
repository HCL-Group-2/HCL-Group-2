import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../model/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: ActivatedRoute,
    private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    // this.updateUser({
    //   id: 1,
    //   firstName: 'Ostrich',
    //   lastName: 'Doe',
    //   email:'ostrich.doe@hcl.com',
    //   password: 'ostrich'
    // });
   }
  updateUser(user: User) {

    this.userService.updateUser(user).subscribe();

  }


}
