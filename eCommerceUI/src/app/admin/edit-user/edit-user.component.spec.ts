<<<<<<< Updated upstream
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserComponent } from './edit-user.component';

describe('EditUserComponent', () => {
  let component: EditUserComponent;
  let fixture: ComponentFixture<EditUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
=======
// import ComponentFixture and TestBed functions.   TestBed is used to configure and initialize the environment unit tests.
import { ComponentFixture, TestBed } from '@angular/core/testing';
// import the EditUserComponent class from the edit-user.component.ts file
import { EditUserComponent } from './edit-user.component';

// beforeEach is a global function in Jasmine that runs some setup code before each spec in the test suite. 
// In this test suite, beforeEach is used to create a testing module using the TestBed object and declares any 
// components that would be used in this testing module. This code creates a version of your Angular application 
// that can be used alongside Jasmine to test component functionality.   
describe('EditUserComponent', () => {
  let component: EditUserComponent;
  let fixture: ComponentFixture<EditUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditUserComponent ]
    })
    .compileComponents();


// The TestBed.createComponent() method is used to create an instance of the AppComponent. 
// The spec then uses expect and matcher functions to see if the component produces the expected behavior. 
// As a result, the spec will either pass or fail. In this case, the expectation is that the AppComponent is defined.    
    fixture = TestBed.createComponent(EditUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
>>>>>>> Stashed changes
