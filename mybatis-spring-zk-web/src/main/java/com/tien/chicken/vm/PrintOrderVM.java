package com.tien.chicken.vm;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.media.ContentTypes;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;

import com.tien.chicken.domain.OrderDetail;
import com.tien.chicken.domain.ReportModel;
import com.tien.chicken.services.OrderService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PrintOrderVM {
	private final static Logger logger = LoggerFactory.getLogger(PrintOrderVM.class);
	private Date startDate;
	private Date endDate;
	@WireVariable
	private OrderService orderService;
	
	@Init
	public void init() {
		
	}
	
	@Command
	public void printOrder1() {
		List<OrderDetail> list = 
				orderService.fetchOderDetailsByDateRange(startDate, endDate);
		ReportModel model = orderService.makeReportModel(list);
		JasperPrint jasperPrint = model.fillReport();
		InputStream jasperReport = model.getReport(jasperPrint);
		Filedownload.save(jasperReport, "PDF", "report");
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
}
