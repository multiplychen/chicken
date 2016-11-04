package com.tien.chicken.services;


import java.util.List;

import com.tien.chicken.domain.Customer;

public interface CustomerService {
	List<Customer> fetchAll();
    void update(Customer customer);
    void delete(Integer custId);
    Customer fetch(Integer custId);
    void insert(Customer customer);

}
