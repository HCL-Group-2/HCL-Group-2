import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { StripeComponent } from './stripe.component';
describe('StripeComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        StripeComponent
      ],
    }).compileComponents();
  });
  it('should create the app', () => {
    const fixture = TestBed.createComponent(StripeComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
  it(`should have as title 'angular'`, () => {
    const fixture = TestBed.createComponent(StripeComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('angular');
  });
  it('should render title', () => {
    const fixture = TestBed.createComponent(StripeComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.content span')?.textContent).toContain('angular app is running!');
  });
});