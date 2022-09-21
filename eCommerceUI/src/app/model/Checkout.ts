

export class Checkout {

    quantity !: number;
    checkout !: CheckOut;
    address !: Address;
    creditCard!: CreditCard;

}
export class CheckOut {
    id !: number;
}

export class Address {
    id !: number;
}
export class CreditCard{
    id!: number;
}