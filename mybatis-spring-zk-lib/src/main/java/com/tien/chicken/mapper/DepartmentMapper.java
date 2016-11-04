package com.tien.chicken.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;

import com.tien.chicken.domain.Customer;

import java.util.List;


@CacheNamespace(readWrite = false )
public interface DepartmentMapper {

	@Select("SELECT id, name FROM DEPARTMENT")
	List<Customer> fetchAll();
}
