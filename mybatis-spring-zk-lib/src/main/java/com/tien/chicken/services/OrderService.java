package com.tien.chicken.services;


import java.util.Date;
import java.util.List;

import com.tien.chicken.domain.Order;
import com.tien.chicken.domain.OrderDetail;
import com.tien.chicken.domain.ReportModel;

public interface OrderService {
	List<OrderDetail> fetchOderDetailsByOrderId(Integer orderId);
    int updateOrder(Order order);
    int updateOrderDetail(OrderDetail orderDetail);
    int updateOrderAndDetails(Order order, List<OrderDetail> orderDetails);
    void deleteOrder(Integer custId);
    Order fetch(Integer custId);
    List<Order> fetchOrder(Integer custId, Date orderDate);
    void insert(Order order, List<OrderDetail> orderDetails);
    ReportModel makeReportModel(List<OrderDetail> list);
    List<OrderDetail> fetchOderDetailsByDateRange(Date startDate, Date endDate);
}
