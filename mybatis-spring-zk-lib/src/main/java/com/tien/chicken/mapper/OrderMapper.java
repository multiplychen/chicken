package com.tien.chicken.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tien.chicken.domain.Order;
import com.tien.chicken.domain.OrderDetail;

public interface OrderMapper {
	List<OrderDetail> fetchOderDetailsByOrderId(Integer orderId);
	List<Order> fetchAll();
	List<Order> fetchOrder(@Param("custId") Integer custId, @Param("orderDate")Date orderDate);
	void update(OrderDetail orderDetail);
	void delete(Integer custId);
	OrderDetail fetch(Integer orderId);
	void insertOrder(Order order);
	void insertOrderDetail(OrderDetail orderDetail);
	int updateOrder(Order order);
	int updateOrderDetail(OrderDetail orderDetail);
	List<OrderDetail> fetchOderDetailsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
