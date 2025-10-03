package com.example.bth11.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bth11.entity.Customer;

@RestController
public class CustomerController {
	final private List<Customer> customers = List.of(
			Customer.builder().id("001").name("Hai Duong").email("haiduong@gmail.comcom").build(),
			Customer.builder().id("002").name("Tam").email("Tam@mail.com").build());

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		String html = """
				<html>
				    <body>
				        <h1>Hello is Guest</h1>
				        <form action="/logout" method="post">
				            <button type="submit">Logout</button>
				        </form>
				    </body>
				</html>
				""";
		return ResponseEntity.ok().header("Content-Type", "text/html").body(html);
	}

	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomersList() {
		List<Customer> list = this.customers;
		return ResponseEntity.ok(list);
	}

	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id) {
		List<Customer> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
}