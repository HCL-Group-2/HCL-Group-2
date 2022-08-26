import { Product } from "./Product";
import { User } from "./User";

export class CartItems{
    
     quantity !:number;
     user !: UserCart;
     product !: ProductCart;

   
   
}
export class UserCart{
     id !: number;
}

export class ProductCart{
     id !: number;
}