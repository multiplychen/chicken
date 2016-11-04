package com.tien.chicken.vm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;

import com.tien.chicken.domain.Customer;
import com.tien.chicken.domain.Order;
import com.tien.chicken.domain.OrderDetail;
import com.tien.chicken.domain.Product;
import com.tien.chicken.services.CustomerService;
import com.tien.chicken.services.OrderService;
import com.tien.chicken.services.ProductService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class Order1VM {
	private final static Logger logger = LoggerFactory.getLogger(Order1VM.class);

	@WireVariable
	private OrderService orderService;
	@WireVariable
	private CustomerService customerService;
	@WireVariable
	private ProductService productService;
	private ListModelList<Customer> customersModel;
	private ListModelList<Product> productsModel;
	private Order order;
	private Customer customer;
	private Date orderDate;
	private OrderDetail orderDetail;
	private List<OrderDetail> orderDetails;
	private List<OrderDetail> orderDetailsTemp;
	private Window win;
	
	@Init
	public void init() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		String today = sdf.format(new Date());
		try {
			orderDate = sdf.parse(today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customersModel = new ListModelList<Customer>(getCustomers());
		customer = customersModel.get(0);
		customersModel.addToSelection(customer);
		productsModel = new ListModelList<Product>(getProducts());
		fetchOrderDetails();
		
//		Executions.createComponents("customer.zul", displayPage, null);
	}
	
	// @NotifyChange({"orderDetails","order"})
	@Command
	public void fetchOrderDetails() {
		order = null;
		orderDetails = null;
		List<Order> orders = orderService.fetchOrder(customer.getCustId(), orderDate);
		if (orders != null && !orders.isEmpty()) {
			this.order = orders.get(0);
			this.orderDetails = orderService.fetchOderDetailsByOrderId(this.order.getOrderId());
			for (OrderDetail orderDetail : orderDetails) {
				
			}
		}
		BindUtils.postNotifyChange(null, null, Order1VM.this, "orderDetails");
		BindUtils.postNotifyChange(null, null, Order1VM.this, "order");
	}
	
	public List<Customer> getCustomers() {
        return customerService.fetchAll();
    }
	
//	@NotifyChange({"orderDetails"})
	@Command
	public void createOrder() {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		Order tempOrder = new Order();
		tempOrder.setCustomer(customer);
		tempOrder.setOrderDate(orderDate);
		params.put("order", tempOrder);
		
		win = (Window) Executions.createComponents("createOrder.zul", null, params);
		BindUtils.postNotifyChange(null, null, Order1VM.this, "orderDetails");
	}
	
	private List<Product> getProducts() {
		return productService.fetchAll();
	}
	
//	@NotifyChange({"customers"})
	@Command
	public void add() {
		Iterator<OrderDetail> it = orderDetailsTemp.iterator();
		while (it.hasNext()) {
			OrderDetail od = it.next();
			if (od.getQuantity() == 0) {
				it.remove();
			}
		}
		
		orderService.insert(order, orderDetails);
		BindUtils.postNotifyChange(null, null, Order1VM.this, "customers");
	}
	
//	@NotifyChange({"order", "orderDetails"})
	@Command
	public void editOrder() {
		boolean isEditFinish = true;
		for (OrderDetail orderDetail :orderDetails) {
			if (orderDetail.getQuantity() == null || orderDetail.getUnitPrice() == null) {
				isEditFinish = false;
				break;
			}
		}
		
		if (isEditFinish) {
			orderService.updateOrderAndDetails(order, orderDetails);
		} else {
			for (OrderDetail orderDetail :orderDetails) {
				orderService.updateOrderDetail(orderDetail);
			}
		}
		BindUtils.postNotifyChange(null, null, Order1VM.this, "orderDetails");
		BindUtils.postNotifyChange(null, null, Order1VM.this, "order");
		Messagebox.show("修改完成", "修改完成視窗", 1, Messagebox.INFORMATION);
	}

	@GlobalCommand
	public void closeWin() {
		win.detach();
	}
	@GlobalCommand
	public void closeWinAndFetchOrder() {
		closeWin();
		fetchOrderDetails();
	}
	
	public List<OrderDetail> getOrderDetails() {
		
		//JRBeanCollectionDataSource j = new JRBeanCollectionDataSource(orderDetails);
		return this.orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ListModelList<Customer> getCustomersModel() {
		return customersModel;
	}

	public void setCustomersModel(ListModelList<Customer> customersModel) {
		this.customersModel = customersModel;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public ListModelList<Product> getProductsModel() {
		return productsModel;
	}

	public void setProductsModel(ListModelList<Product> productsModel) {
		this.productsModel = productsModel;
	}
}
