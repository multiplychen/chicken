package com.tien.chicken.vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.tien.chicken.domain.Order;
import com.tien.chicken.domain.OrderDetail;
import com.tien.chicken.domain.Product;
import com.tien.chicken.services.CustomerService;
import com.tien.chicken.services.OrderService;
import com.tien.chicken.services.ProductService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CreateOrderVM {
	private final static Logger logger = LoggerFactory.getLogger(CreateOrderVM.class);

	@WireVariable
	private OrderService orderService;
	@WireVariable
	private CustomerService customerService;
	@WireVariable
	private ProductService productService;
	
	private Order order;
	private List<OrderDetail> orderDetailsTemp;
	
	@Init
	public void init() {
		
		this.order = (Order) Executions.getCurrent().getArg().get("order");
		createOrderDetails();
	}
	
	private void createOrderDetails() {
 
		List<Product> products = getProducts();
		orderDetailsTemp = new ArrayList<OrderDetail>();
		
		for (Product product : products) {
			if (product.getProdId() <= 19) {
				
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProduct(new Product());
				orderDetail.getProduct().setProdId(product.getProdId());
				orderDetail.getProduct().setProdName(product.getProdName());
				orderDetailsTemp.add(orderDetail);
				
			}
		}
		BindUtils.postNotifyChange(null, null, CreateOrderVM.this, "orderDetailsTemp");
	}
	
	private List<Product> getProducts() {
		return productService.fetchAll();
	}
	
	@Command
	public void add() {
		Iterator<OrderDetail> it = orderDetailsTemp.iterator();
		while (it.hasNext()) {
			OrderDetail od = it.next();
			if (od.getUnitQty() == null) {
				it.remove();
			}
		}
		if (orderDetailsTemp.isEmpty()) {
			return;
		}
		orderService.insert(order, orderDetailsTemp);
		BindUtils.postGlobalCommand(null, null, "closeWinAndFetchOrder", null);
	}
	
	private void addOrder(Order order) {
//		orderService.insert(order);
	}
	
	private void addOrderDetails(Order order) {
//		orderService.insert(order);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderDetail> getOrderDetailsTemp() {
		return orderDetailsTemp;
	}

	public void setOrderDetailsTemp(List<OrderDetail> orderDetailsTemp) {
		this.orderDetailsTemp = orderDetailsTemp;
	}
}
