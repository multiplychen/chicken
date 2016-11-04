package com.tien.chicken.vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.tien.chicken.domain.Customer;
import com.tien.chicken.services.CustomerService;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerVM {
	private final static Logger logger = LoggerFactory.getLogger(CustomerVM.class);

	@WireVariable
	private CustomerService customerService;

	private Customer customer;
	private boolean hideForm = true;
	List<Customer> customers;
	@Init
	public void init() {
		customers = customerService.fetchAll();
	}
	
	@NotifyChange({"customers", "hideForm"})
	@Command
	public void createCustomer() {
		customer = new Customer();
		List<Customer> ct = getCustomers();
		ct.add(customer);
		hideForm = false;
	}

	@NotifyChange({"hideForm"})
	@Command
	public void editCustomer() {
		if (customer != null) hideForm = false;
	}

	@NotifyChange({"customers", "hideForm"})
	@Command
	public void add() {
		customerService.insert(customer);
		hideForm = true;
	}

	@NotifyChange({"customers", "hideForm"})
	@Command
	public void update() {
		customerService.update(customer);
		hideForm = true;
	}

	@NotifyChange({"customers", "hideForm"})
	@Command
	public void delete() {
		customerService.delete(customer.getCustId());
		hideForm = true;
	}

	@NotifyChange({"customers", "hideForm"})
	@Command
	public void cancel() {
		hideForm = true;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public boolean isHideForm() {
		return hideForm;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
