import { Address } from "./Address";
import { CartItems } from "./CartItems";

export class CheckoutOrder{
   
    user !: CheckoutUser;
    shippingAddress !:  CheckoutAddress;
    payment !: CheckoutCard;

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

// {
//     "user": {"firstName": "Test", "lastName": "User", "email": "testuser@gmail.com"},
//     "shippingAddress": {"address1": "1234 Test Address A", "address2": null, "city": "Frisco", "state": "Texas", "zipCode": "75034"},
//     "payment": {"name": "Test Name", "creditCardNumber": "1234567812345678", "expirationDate": "2024-01-01"}
// }