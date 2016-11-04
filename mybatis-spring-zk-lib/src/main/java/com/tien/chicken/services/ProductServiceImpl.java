package com.tien.chicken.services;


import org.springframework.stereotype.Service;

import com.tien.chicken.domain.Product;
import com.tien.chicken.mapper.ProductMapper;

import javax.annotation.Resource;

import java.util.List;

/*
In real life your service methods might do a bit more than just delegate to the mapper.
The service just shields we are using mybatis and we'd put other stuff in here.
Remember, nothing wrong if you feel like just using MyBatis mappers directly in your
ViewModels. In this case you can skip using the Service class approach
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductMapper productMapper;

	@Override
	public List<Product> fetchAll() {
		return productMapper.fetchAll();
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer prodId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product fetch(Integer prodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		
	}

}
