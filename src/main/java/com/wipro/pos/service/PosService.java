package com.wipro.pos.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.wipro.pos.bean.PosBean;
import com.wipro.pos.dao.PosDAO;
import com.wipro.pos.util.InvalidInputException;

public class PosService {
	
	public String addRecord(PosBean bean) {
		PosDAO posDAO=new PosDAO();
		String result="";
		try {
		if(bean==null || bean.getCustomerName()==null || bean.getItemName() == null || bean.getTransDate()==null) {
			throw new InvalidInputException("Invalid Input");
		}
		String customerName=bean.getCustomerName();
		String itemName=bean.getItemName();
		int quality=bean.getQuantity();
		double price=bean.getPrice();
		if(customerName.length()<2) {
			return "Invalid Customer Name";
		}if(itemName.length()<2) {
			return "Invalid item Name";
		}
		if(quality<1 || price<=0) {
			return "Invalid Transaction";
		}
		if(posDAO.recordExists(customerName,bean.getTransDate())) {
			return "Already exists";
		}
		
		String transId=posDAO.generateTransID(customerName, bean.getTransDate());
		bean.setTransId(transId);
		Double tamount=quality*price;
		bean.setTotalAmount(tamount);
		result=posDAO.createRecord(bean);
		
		}catch(InvalidInputException e) {
			return e.toString()
;		}
		return result;	
	}
	
	public PosBean viewRecord(String customerName, Date transDate) {
		PosDAO posDAO=new PosDAO();
		PosBean bean=new PosBean();
		bean=posDAO.fetchRecord(customerName, transDate);
		return bean;
		
	}
	
	public List<PosBean> viewAllRecords() {
		List<PosBean> list=new ArrayList<PosBean>();
		PosDAO posDAO=new PosDAO();
		list=posDAO.fetchAllRecords();
		return list;
	}
	
	

}
