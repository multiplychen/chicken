package com.tien.chicken.vm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class IndexVM {
	private final static Logger logger = LoggerFactory.getLogger(IndexVM.class);
	private String displayPage;
	
	@Init
	public void init() {
		
	}
	
	@Command
	public void changePage(@BindingParam("pageUrl") String pageUrl) {
//		Executions.sendRedirect(pageUrl);
		setDisplayPage(pageUrl);
		BindUtils.postNotifyChange(null, null, IndexVM.this, "displayPage");
	}

	public String getDisplayPage() {
		return displayPage;
	}

	public void setDisplayPage(String displayPage) {
		this.displayPage = displayPage;
	}
	
}
