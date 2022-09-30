import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, IDToken, UserClaims } from '@okta/okta-auth-js';

import { HeaderComponent } from './header.component';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;

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
      declarations: [HeaderComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule
        // MatDialogModule
      ],
      providers: [FormBuilder,
        { provide: OKTA_AUTH, useValue: oktaAuthSpy },
        { provide: OktaAuthStateService, useValue: authStateSpy }]
    }).compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
