package com.tien.chicken.services;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tien.chicken.domain.Order;
import com.tien.chicken.domain.OrderDetail;
import com.tien.chicken.domain.ReportModel;
import com.tien.chicken.mapper.OrderMapper;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderMapper orderMapper;

	@Override
	public List<OrderDetail> fetchOderDetailsByOrderId(Integer orderId) {

		return orderMapper.fetchOderDetailsByOrderId(orderId);
	}

	@Override
	public int updateOrder(Order order) {
		return orderMapper.updateOrder(order);
	}
	
	@Override
	public int updateOrderDetail(OrderDetail orderDetail) {
		return orderMapper.updateOrderDetail(orderDetail);		
	}
	
	@Override
	public int updateOrderAndDetails(Order order, List<OrderDetail> orderDetails) {
		
		int updateCount = 0;
		
		for (OrderDetail orderDetail : orderDetails) {
			updateCount += updateOrderDetail(orderDetail);
		}
		
		order.setOrderStat("B");
		updateOrder(order);
		
		return updateCount;
	}

	@Override
	public void deleteOrder(Integer custId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order fetch(Integer custId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insert(Order order, List<OrderDetail> orderDetails) {
		
		orderMapper.insertOrder(order);
		
		for (OrderDetail orderDetail : orderDetails) {
			
			orderDetail.setOrder(new Order());
			orderDetail.getOrder().setOrderId(order.getOrderId());
			orderMapper.insertOrderDetail(orderDetail);
			
		}
		
	}


	@Override
	public List<Order> fetchOrder(Integer custId, Date orderDate) {
		
		return orderMapper.fetchOrder(custId, orderDate);
	}

	@Override
	public ReportModel makeReportModel(List<OrderDetail> list) {
		ReportModel model = new ReportModel();
		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("list", list);
		model.setJasperParameter(param);
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(list);
		model.addDataSource("list1", dataSource);
		model.setTemplateName("order_detail");
		
		return model;
	}

	@Override
	public List<OrderDetail> fetchOderDetailsByDateRange(Date startDate,
			Date endDate) {
		return orderMapper.fetchOderDetailsByDateRange(startDate, endDate);
	}


}
