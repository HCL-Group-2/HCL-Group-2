import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from '../model/Order';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent{

  order !: Order;

  allOrders !: Array<Order>;
  name = 'Order Status';

  storage: Storage =sessionStorage;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private OrderService: OrderService) {}

    ngOnInit(): void {
      let userId = +this.storage.getItem('userId')!;
      this.getUserOrders(userId);

    }

    getUserOrders(userId: number) {
      this.OrderService.getUserOrders(userId).subscribe(data => {
        this.allOrders = data;
      });
    }

  public status = ["Ordered","In Progress","Shipping","Arriving","Delivered"];


}
