package com.tien.chicken.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportModel {
	private String templateName;
	private Map<String, Object> jasperParameter;
	private Map<String, JRDataSource> dataSource;
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Map<String, Object> getJasperParameter() {
		return jasperParameter;
	}
	public void setJasperParameter(Map<String, Object> jasperParameter) {
		this.jasperParameter = jasperParameter;
	}
	public Map<String, JRDataSource> getDataSource() {
		return dataSource;
	}
	public void setDataSource(Map<String, JRDataSource> dataSource) {
		this.dataSource = dataSource;
	}
	public void addDataSource(String srcDataSourceName, JRDataSource srcDataSource) {
		this.dataSource.put(srcDataSourceName, srcDataSource);
	}
	public JasperPrint fillReport() {
		JasperPrint jasperPrint = null;
		String jasperPath = new StringBuilder().append("").append(templateName).
				append(".jasper").toString();
		try {
		InputStream jasperTemplate = ReportModel.class.getClassLoader()
				.getResourceAsStream(jasperPath);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperTemplate);
		Collection<JRDataSource> values = dataSource.values();
		JRDataSource jasperDataSource = values.iterator().next();
		jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, jasperDataSource);
		} catch (Exception e) {
			e.printStackTrace();
		};
		return jasperPrint;
	}
	public InputStream getReport(JasperPrint jasperPrint) {
		JRPdfExporter jrExporter = new JRPdfExporter();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		try {
			jrExporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
