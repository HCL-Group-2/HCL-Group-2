export class CheckoutOrder{
    user !: CheckoutUser;
    shippingAddress !:  CheckoutAddress;
}

export class CheckoutUser{
    firstName !: string;
    lastName !: string;
    email !: string;
}

export class CheckoutCard{   
    name !: string;
    creditCardNumber !: string;
    expirationDate !: string;
}

export class CheckoutAddress{   
    id ?: number;
    address1 !: string;
    address2 ?: string;
    city !: string;
    state !: string;
    zipCode !: string;
}