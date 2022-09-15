import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  userId !: number;
  userEditForm !: FormGroup
  editUser !: User;

  constructor(private router: Router, private route: ActivatedRoute,
    private userService: UserService,
    public editUserDialog: MatDialog) {

    this.userId = this.route.snapshot.params['userId'];
    console.log('userId ' + this.userId);

    this.userEditForm = new FormGroup({
      id: new FormControl({ value: this.userId, disabled: true }),
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password: new FormControl('')
    });

  }

  ngOnInit(): void {
    this.userService.getUser(this.userId).subscribe(data => {
      this.userEditForm.setValue({
        id: data.id,
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
        password: data.password
      })
    })
  }

  editUserSubmit(event: any) {
    console.log('id in form ' + this.userEditForm.get('id')?.value);

    let userFirstName = this.userEditForm.get('firstName')?.value;
    console.log('first name ' + userFirstName);
    let userLastName = this.userEditForm.get('lastName')?.value;
    console.log('first name' + userLastName);
    let userEmail = this.userEditForm.get('email')?.value;
    console.log('email' + userEmail);
    let userPassword = this.userEditForm.get('password')?.value;
    console.log('password' + userPassword);


    this.editUser = {
      "id": this.userId,
      "firstName": userFirstName,
      "lastName": userLastName,
      "email": userEmail,
      "password": userPassword
    };

    console.log('edit user: ' + JSON.stringify(this.editUser));
    // this.productService.updateProduct(this.editProduct).subscribe();
    this.userService.updateUser(this.editUser).subscribe();

    const dialogRef = this.editUserDialog.open(UserEditDialog, {
      data: {
        name: ' in the UserEditDialog placeholder',
      }, disableClose: true
    },);

    dialogRef.afterClosed().subscribe(() => {
      console.log('edit user dialog box is closed.');
      this.router.navigate(['admin']);
      // window.location.reload();
    });

  }


}
@Component({
  selector: 'user-edit-dialog',
  templateUrl: 'user-edit-dialog.html',
})
export class UserEditDialog {
  constructor(
    public dialogRef: MatDialogRef<UserEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {
    dialogRef.disableClose = true;
  }

  onNoClick(): void {

    this.dialogRef.close();
  }
}

