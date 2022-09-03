import { CartItems } from "./CartItems";

export class CheckoutOrder{
    id!:number;
    items!:CartItems[];
    totalPrice!:number;
    name!:string;
    address!:string;
    paymentId!:string;
    createdAt!:string;
    status!:string;

}