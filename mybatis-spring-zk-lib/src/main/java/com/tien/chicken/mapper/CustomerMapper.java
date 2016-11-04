package com.tien.chicken.mapper;

import java.util.List;

import com.tien.chicken.domain.Customer;

public interface CustomerMapper {
	List<Customer> fetchAll();
	void update(Customer customer);
	void delete(Integer custId);
	Customer fetch(Integer custId);
	void insert(Customer customer);
}
