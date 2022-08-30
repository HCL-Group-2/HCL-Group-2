import { Component} from '@angular/core';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent{

  name = 'Order Status';

  public status = ["Received","In Progress","Shipping","Arriving","Delivered"];
  public orderStatus = "In Progress"
  //constructor() { }

}
