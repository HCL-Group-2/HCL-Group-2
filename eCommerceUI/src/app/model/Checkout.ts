

export class Checkout {

    quantity !: number;
    user !: User;
    address !: Address;
    creditCard!: CreditCard;

}
export class User {
    id !: number;
}

export class Address {
    id !: number;
}
export class CreditCard{
    id!: number;
}