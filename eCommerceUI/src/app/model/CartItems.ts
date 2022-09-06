import { Product } from "./Product";
import { User } from "./User";


export class CartItems {

     quantity !: number;
     user !: UserCart;
     product !: ProductCart;

}

export class UserCart {
     id !: number;
}

export class ProductCart {
     id !: number;
}

export class CartItems2 {
     id ?: number;
     quantity !: number;
     subtotal ?: number;
     user !: User;
     product !: Product;

}