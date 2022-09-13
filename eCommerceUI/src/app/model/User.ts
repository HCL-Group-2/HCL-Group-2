import { Order } from "./Order";

export class User{
    
    id !: number;
    firstName !: string;
    lastName !: string;
    email !: string;
    password !: string;
	
}
export class UserOnAdmin{
    id !: number;
    firstName !: string;
    lastName !: string;
    email !: string;
    pendingOrders ?: number;
	
}
