<<<<<<< Updated upstream
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

=======
// @angular/core implements Angular's core functionality, low-level services, and utilities.
// Component is a decorator that marks a class as an Angular component and provides configuration metadata that determines 
// how the component should be processed, instantiated, and used at runtime.
// Inject is a parameter decorator on a dependency parameter of a class constructor that specifies a custom provider of the dependency.
// OnInit is a lifecycle hook that is called after Angular has initialized all data-bound 
// properties of a directive. Define an ngOnInit() method to handle any additional initialization tasks.
import { Component, Inject, OnInit } from '@angular/core';
// @angular/forms implements a set of directives and providers to communicate with native DOM (Document Object Model which is
// a programming interface for web documents) elements when building forms to capture user input.
// FormControl tracks the value and validation status of an individual form control.
// FormGroup tracks the value and validity state of a group of FormControl instances.
// Validators provides a set of built-in validators that can be used by form controls.
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
// Router is a service that provides navigation among views and URL manipulation capabilities.
// ActivatedRoute provides access to information about a route associated with a component that is loaded in an outlet. Use to traverse the 
// RouterState tree and extract information from nodes.    
import { ActivatedRoute, Router } from '@angular/router';
// 
import { User } from 'src/app/model/User';
//
import { UserService } from 'src/app/user.service';


@Component({
  // The CSS selector that identifies this directive in a template and triggers instantiation of the directive.
  selector: 'app-edit-user',
  // The relative path or absolute URL of a template file for an Angular component.
  templateUrl: './edit-user.component.html',
  // One or more relative paths or absolute URLs for files containing CSS stylesheets to use in this component.
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  userId !: number;
  userEditForm !: FormGroup
  editUser !: User;

  // The constructor is a method in a TypeScript class that gets called when the class is being 
  // instantiated. It’s not an Angular feature but rather a concept that's present in most 
  // Object-Oriented languages including TypeScript. This constructor has private object variables of
  // router, route, userService, and editUserDialog
  constructor(private router: Router, private route: ActivatedRoute,
    private userService: UserService,
    public editUserDialog: MatDialog) {

  // Get the the userId variable from the user.service.ts file that can be found in the current Activated Route and put it in this.userId    
    this.userId = this.route.snapshot.params['userId'];
  // console.log is a JavaScript function that logs messages to the developer’s console or the browser console to the web page. This
  // one is set to display in text the userId variable
    console.log('userId ' + this.userId);

  //   
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

>>>>>>> Stashed changes
