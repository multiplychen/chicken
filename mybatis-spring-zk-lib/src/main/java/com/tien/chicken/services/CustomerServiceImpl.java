package com.tien.chicken.services;

import org.springframework.stereotype.Service;

import com.tien.chicken.domain.Customer;
import com.tien.chicken.mapper.CustomerMapper;

import javax.annotation.Resource;

import java.util.List;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerMapper customerMapper;

	public List<Customer> fetchAll() {
		return customerMapper.fetchAll();
	}

	public void update(Customer emp) {
		customerMapper.update(emp);
	}

	public void delete(Integer id) {
		customerMapper.delete(id);
	}

	public Customer fetch(Integer custId) {
		return customerMapper.fetch(custId);
	}

	public void insert(Customer customer) {
		customerMapper.insert(customer);
	}
}
