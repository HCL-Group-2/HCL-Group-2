import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { RouterTestingModule } from '@angular/router/testing';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { of } from 'rxjs';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  const authStateSpy = jasmine.createSpyObj('OktaAuthStateService', [], {
    authState$: of({
      isAuthenticated: false
    })
  });

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeComponent ],
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatDialogModule
    ],
    providers:[FormBuilder,OktaAuthStateService,
              {provide: OKTA_AUTH, useValue:authStateSpy  }]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
