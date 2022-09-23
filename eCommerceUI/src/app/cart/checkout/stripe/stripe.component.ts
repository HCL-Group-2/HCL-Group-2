import { Component } from '@angular/core';
@Component({
  selector: 'stripe-root',
  templateUrl: './stripe.component.html',
  styleUrls: ['./stripe.component.css'],
})
export class StripeComponent {
  paymentHandler: any = null;
  // for backend
  session: Storage = sessionStorage;
  title = 'angualr-stripe-example'
  // for backend
  checkoutTotal: number = 0.0;
  email: String = "";

  constructor() { }
  ngOnInit() {
    this.checkoutTotal = Number(this.session.getItem('total')!);
    this.email = this.session.getItem('email')!;

    this.invokeStripe();
  }
  makePayment(amount: any) {
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: 'pk_test_51LdFEeBnlJ0Cu1G7C9I2DKFKPrHmbOww4LqibQ36o8PTJUd66u3DhDLuTq10Mvd9a1WXTDiBU1NbbHvsEGytWcBn00lor6i3MS',
      locale: 'auto',
      token: function (stripeToken: any) {
        console.log(stripeToken);
        alert('Stripe token generated!');
      },
    });
    paymentHandler.open({
      name: 'Ostrichmart',
      description: '3 widgets',
      amount: amount * 100,
    });
  }
  invokeStripe() {
    if (!window.document.getElementById('stripe-script')) {
      const script = window.document.createElement('script');
      script.id = 'stripe-script';
      script.type = 'text/javascript';
      script.src = 'https://checkout.stripe.com/checkout.js';
      script.onload = () => {
        this.paymentHandler = (<any>window).StripeCheckout.configure({
          key: 'pk_test_51LdFEeBnlJ0Cu1G7C9I2DKFKPrHmbOww4LqibQ36o8PTJUd66u3DhDLuTq10Mvd9a1WXTDiBU1NbbHvsEGytWcBn00lor6i3MS',
          locale: 'auto',
          token: function (stripeToken: any) {
            console.log(stripeToken);
            alert('Payment has been successfull!');
          },
        });
      };
      window.document.body.appendChild(script);
    }
  }
}