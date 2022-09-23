import { Product } from "./Product";
import { User } from "./User";

export class Order{
    user !: User;
    id !: number;
    orderDate !: Date;
    orderTotal !: number;
    orderStatus !: string;
    orderItems !: Array<OrderItems>;
  }

  export class OrderItems {
    quantity !: number;
    product !: Product;

}

