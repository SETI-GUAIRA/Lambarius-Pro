package br.gov.pr.guaira.lambarius.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;



@Service
public class JasperService {
	@Autowired
	private Connection connection;
	
	private Map<String, Object> params = new HashMap<>();
	
	
	public void addParams(String Key, Object value) {
		this.params.put(Key, value);
	}
	
	public byte[] exportar_Carteirinha_Individual_PDF(){
		byte[] bytes = null;
		try {			
			File file = ResourceUtils.getFile("classpath:jasper/carteirinha_individual.jasper");
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
		    bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	
	}
	
	public byte[] exportar_Carteirinha_Associacao_PDF(){
		byte[] bytes = null;
		try {			
			File file = ResourceUtils.getFile("classpath:jasper/carteirinha_associacao.jasper");
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
		    bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	
	}
	
	public byte[] exportar_Carteirinha_Todas_PDF(){
		byte[] bytes = null;
		try {			
			File file = ResourceUtils.getFile("classpath:jasper/carteirinha_todas.jasper");
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
		    bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	
	}
	
}
