import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { RouterTestingModule } from '@angular/router/testing';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { of } from 'rxjs';

import { HomeComponent } from './home.component';
import { AuthState, IDToken, UserClaims } from '@okta/okta-auth-js';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  const claims: UserClaims = {
    sub: 'sub',
    name: 'Test Name'
  };

  const idToken: IDToken = {
    idToken: 'token',
    clientId: 'client',
    issuer: 'issuer',
    authorizeUrl: 'authorize',
    expiresAt: 123,
    scopes: [],
    claims
  };

  const authState: AuthState = {
    isAuthenticated: true,
    idToken
  };

  let authStateSpy = jasmine.createSpyObj<OktaAuthStateService>([], ['authState$']);

  let oktaAuthSpy = jasmine.createSpyObj('OktaAuth', ['login']);

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      declarations: [HomeComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule,
        MatDialogModule
      ],
      providers: [FormBuilder,
        { provide: OKTA_AUTH, useValue: oktaAuthSpy },
        { provide: OktaAuthStateService, useValue: authStateSpy }
      ],

    }).compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    (Object.getOwnPropertyDescriptor(authStateSpy, 'authState$')?.get as jasmine.Spy).and.returnValue(of(authState));
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
