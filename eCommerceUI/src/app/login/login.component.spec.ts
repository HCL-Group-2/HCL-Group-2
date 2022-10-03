import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, IDToken, UserClaims } from '@okta/okta-auth-js';
import { of } from 'rxjs';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

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
      declarations: [LoginComponent],
      imports: [
        // RouterTestingModule,
        HttpClientModule
        // MatDialogModule
      ],
      providers: [
        { provide: OKTA_AUTH, useValue: oktaAuthSpy },
        { provide: OktaAuthStateService, useValue: authStateSpy }]
    }).compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    (Object.getOwnPropertyDescriptor(authStateSpy, 'authState$')?.get as jasmine.Spy).and.returnValue(of(authState));
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
