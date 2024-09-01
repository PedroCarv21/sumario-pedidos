package entities;

import java.util.ArrayList;
import java.util.List;
import java.time.Instant;

import entities.enums.OrderStatus;

public class Order {
	private Instant moment;
	private OrderStatus status;
	private Client client;
	
	private List<OrderItem> items = new ArrayList<>();
	
	public Order(Instant moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	
	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void addItem(OrderItem item) {
		this.items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		this.items.remove(item);
	}
	
	public Double total() {
		Double totalPrice = 0.0;
		for (OrderItem item: this.items) {
			totalPrice += item.subTotal();
		}
		return totalPrice;
	}
	
	public String toString() {
		for (OrderItem item: this.items) {
			System.out.printf("%s, $%s, Quantity: %d, Subtotal: $%.2f%n", item.getProduct().getName(), item.getPrice(), item.getQuantity(), item.subTotal());
		}
		
		return String.format("Total price: $%.2f", this.total());
	}
}
