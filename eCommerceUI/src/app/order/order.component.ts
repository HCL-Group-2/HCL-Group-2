import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit{

  name = 'Order Status';


  //States the order can be in
  public counts = ["Received", "In Progress", "Arriving", "Order Delivered"];
  //Test for output
  public orderStatus = "Arriving";

  //constructor() { }

  ngOnInit(): void {

  }


}
