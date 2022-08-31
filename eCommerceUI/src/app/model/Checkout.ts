

export class Checkout {

    quantity !: number;
    orderNumber !: OrderNumber;
    address !: Address;
    creditCard!: CreditCard;

}
export class OrderNumber {
    id !: number;
}

export class Address {
    id !: number;
}
export class CreditCard{
    id!: number;
}