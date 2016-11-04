package com.tien.chicken.mapper;

import java.util.List;

import com.tien.chicken.domain.Product;

public interface ProductMapper {
	List<Product> fetchAll();
	void update(Product product);
	void delete(Integer prodId);
	Product fetch(Integer prodId);
	void insert(Product product);
}
