import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserOnAdmin } from '../model/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  usersOnAdmin !: Array<UserOnAdmin>;

  constructor(private route: ActivatedRoute, private router: Router,
    private userService: UserService) { }

  ngOnInit(): void {
    this.getUsersOnAdmin();
  }

  getUsersOnAdmin(){
    this.userService.getAllUsers().subscribe(data => {
      this.usersOnAdmin = data;
      console.log(JSON.stringify(this.usersOnAdmin));
    });
  }
  
  goToEditUserForm(userId: number) {
    console.log(' goToEditUserForm userId: ' + userId);
    this.router.navigate(['admin/editUser/',userId]);
  }
}