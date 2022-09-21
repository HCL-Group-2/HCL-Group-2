import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CartService } from 'src/app/cart.service';
import { CheckoutService } from 'src/app/checkout.service';
import { Address } from 'src/app/model/Address';
import { CartItems2 } from 'src/app/model/CartItems';
import { CheckoutAddress, CheckoutCard, CheckoutOrder, CheckoutUser } from 'src/app/model/CheckoutOrder';
import { PaymentIntent } from 'src/app/model/PaymentIntent';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutForm: FormGroup = new FormGroup([]);
  cartItemsCheckout !: Array<CartItems2>
  checkoutTotal: number = 0;

  userCheckout !: CheckoutUser;
  userShippingAddress !: CheckoutAddress;
  userPayment !: CheckoutCard;
  orderCheckOut !: CheckoutOrder;
  session: Storage = sessionStorage;

  paymentIntent: PaymentIntent = new PaymentIntent();

  constructor(private formBuilder: FormBuilder, private cartService: CartService,
    private checkOutService: CheckoutService, public checkoutDialog: MatDialog,
    private router: Router
  ) {

  }

  ngOnInit(): void {

    this.checkoutForm = this.formBuilder.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      shippingaddress: this.formBuilder.group({
        address1: ['', [Validators.required]],
        address2: [''],
        city: ['', [Validators.required]],
        state: ['', [Validators.required]],
        zipcode: ['', [Validators.required]]
      }),
      payment: this.formBuilder.group({
        nameOnCard: ['', [Validators.required]],
        creditCardNumber: ['', [Validators.required]],
        expirationDate: ['', [Validators.required]],
        verificationCode: ['']
      })
    })
    let userId = +this.session.getItem('userId')!;

    this.getCartItems(userId);

  }


  getCartItems(userId: number) {

    this.cartService.getCartItems(userId).subscribe(data => {
      this.cartItemsCheckout = data;

      for (let cc in this.cartItemsCheckout) {
        this.checkoutTotal += (this.cartItemsCheckout[cc].quantity * this.cartItemsCheckout[cc].product.price);
        //this.cartItemsCheckout[cc].subtotal = Math.round(this.cartItemsCheckout[cc].subtotal * 100) / 100;
        this.checkoutTotal = Math.round(this.checkoutTotal * 100) / 100;

        // for backend
        this.session.setItem('total', this.checkoutTotal.toString());

      }

      Source: https://www.holadevs.com/pregunta/107232/how-can-i-add-up-the-total-in-ngfor
      console.log(JSON.stringify(this.cartItemsCheckout));
    }

    );
  }

  checkout(event: any) {
    let userFirstName = this.checkoutForm.get('firstname')?.value;
    console.log('userFirstName ' + userFirstName);
    let userLastName = this.checkoutForm.get('lastname')?.value;
    console.log('userLastName ' + userLastName);
    let userEmail = this.checkoutForm.get('email')?.value;
    console.log('email ' + userEmail);
    let address1 = this.checkoutForm.get('shippingaddress')?.get('address1')?.value;
    console.log('address1 ' + address1);
    let address2 = this.checkoutForm.get('shippingaddress')?.get('address2')?.value;
    console.log('address2 ' + address2);
    if (address2.length == 0) {
      address2 = null;
    }
    let city = this.checkoutForm.get('shippingaddress')?.get('city')?.value;
    console.log('city ' + city);
    let state = this.checkoutForm.get('shippingaddress')?.get('state')?.value;
    console.log('state ' + state);
    let zipcode = this.checkoutForm.get('shippingaddress')?.get('zipcode')?.value;
    console.log('zipcode ' + zipcode);
    // let nameOnCard = this.checkoutForm.get('payment')?.get('nameOnCard')?.value;
    // console.log('nameOnCard ' + nameOnCard);
    // let creditCardNumber = this.checkoutForm.get('payment')?.get('creditCardNumber')?.value;
    // console.log('creditCardNumber ' + creditCardNumber);
    // let expirationDate = this.checkoutForm.get('payment')?.get('expirationDate')?.value;
    // console.log('expirationDate  ' + expirationDate);

    // we don't store cvv in the database for security purpose

    // this.userCheckout = { firstName: userFirstName, lastName: userLastName, email: userEmail };
    // this.userShippingAddress = { "address1": address1, "address2": address2, "city": city, "state": state, "zipCode": zipcode };
    // this.userPayment = { "name": nameOnCard, "creditCardNumber": creditCardNumber, "expirationDate": expirationDate };

    

    this.orderCheckOut = { "user": this.userCheckout, "shippingAddress": this.userShippingAddress/*, "payment": this.userPayment */ };
    console.log('orderCheckout ' + JSON.stringify(this.orderCheckOut));

    // call
    this.paymentIntent.email = this.userCheckout.email;
    this.paymentIntent.total = this.checkoutTotal;

    var clientSecret = this.checkOutService.paymentIntent(this.paymentIntent);
    console.log('paymentIntent' +JSON.stringify(clientSecret));

    if (clientSecret) {
      this.router.navigate(['/checkout-payment']);
    }

    this.checkOutService.addOneCheckout(this.orderCheckOut).subscribe();

    const dialogRef = this.checkoutDialog.open(CheckoutDialog, {
      data: {
        name: ' in the checkout placeholder',
      },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('edit product dialog box is closed.');
      this.router.navigate(['/home']).then(() => {
        window.location.reload();
      });
    });

  }

  stripeCheckoutPage(){
 
    this.router.navigate(['/checkout-payment']);
  }

}
@Component({
  selector: 'checkoutDialog-dialog',
  templateUrl: 'checkoutDialog-dialog.html',
})
export class CheckoutDialog {
  constructor(public dialogRef: MatDialogRef<CheckoutDialog>, private router: Router,
    @Inject(MAT_DIALOG_DATA) public data: { name: string }) { }

  onNoClick(): void {
    this.dialogRef.close();

  }

}