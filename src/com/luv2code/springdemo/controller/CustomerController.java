package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String getCustomers(Model model) {
		List<Customer> customer = customerService.getCustomers();
		model.addAttribute("customers",customer);		
		return "list-customers";
	}
	@GetMapping("/showFormAdd")
	public String addCustomer(Model model) {
		//create model attribute to bind form data....
		Customer theCustomer =  new Customer();
		model.addAttribute("customer",theCustomer);
		return "customer-form";
	}	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer customer) {
		//save customer using service...
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	@GetMapping("/showFormForUpdate")
	public String updateCustomer(@RequestParam("customerId") int theId, Model model) {
		//get the customer from the database
		Customer customer = customerService.getCustomer(theId);
		//set the customer as a model attribute to pre-populate the form
		model.addAttribute("customer",customer);
		//send over to our form
		return "customer-form";
	}
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		//delete the customer
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}
