
import { TestBed } from '@angular/core/testing';
import { OKTA_AUTH } from '@okta/okta-angular';

import { AuthInterceptor } from './auth.interceptor';

describe('AuthInterceptor', () => {

  let oktaAuthSpy = jasmine.createSpyObj('OktaAuth', ['login']);

  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      AuthInterceptor,
      { provide: OKTA_AUTH, useValue: oktaAuthSpy }
      ]
  }));

  it('should be created', () => {
    const interceptor: AuthInterceptor = TestBed.inject(AuthInterceptor);
    expect(interceptor).toBeTruthy();
  });
});

