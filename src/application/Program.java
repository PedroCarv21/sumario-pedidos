package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;


import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.CANADA);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter client data: ");
		System.out.print("Name: ");
		String name = sc.nextLine();
		
		System.out.print("Email: ");
		String email = sc.nextLine();
		
		System.out.print("Birth date (DD/MM/YYYY): ");
		LocalDate birthDate = LocalDate.parse(sc.nextLine(), format);
		
		Client client = new Client(name, email, birthDate);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		String status = sc.nextLine().toUpperCase();
		
		Instant now = Instant.now();
		
		Order order = new Order(now, OrderStatus.valueOf(status), client);
		
		System.out.print("How many items to this order? ");
		Integer numberOfItems = Integer.parseInt(sc.nextLine());
		
		for (int item = 1; item <= numberOfItems; item++) {
			System.out.printf("Enter #%d item data: %n", item);
			System.out.print("Product name: ");
			String productName = sc.nextLine();
			System.out.print("Product price: ");
			Double price = Double.parseDouble(sc.nextLine());
			Product product = new Product(productName, price);
			System.out.print("Quantity: ");
			Integer quantity = Integer.parseInt(sc.nextLine());
			OrderItem orderItem = new OrderItem(quantity, product.getPrice(), product);
			order.addItem(orderItem);
			
		}
		
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
		
		System.out.println();
		System.out.println("ORDER SUMMARY:");
		System.out.printf("Order moment: %s%n", format2.format(order.getMoment()));
		System.out.printf("Order status: %s%n", order.getStatus());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.printf("Client: %s (%s) - %s%n", order.getClient().getName(), dtf.format(order.getClient().getBirthDate()), order.getClient().getEmail());
		System.out.println("Order items:");
		System.out.println(order);
		sc.close();
	}

}
