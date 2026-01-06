package com.wipro.pos.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.wipro.pos.bean.PosBean;
import com.wipro.pos.dao.PosDAO;
import com.wipro.pos.service.PosService;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	public String addRecord(HttpServletRequest request) {
		PosBean bean=new PosBean();
		bean.setCustomerName(request.getParameter("customer"));
		bean.setItemName(request.getParameter("item"));
		String date=request.getParameter("date");
		Date ogdate=Date.valueOf(date);
		bean.setTransDate(ogdate);
		int quality=Integer.parseInt(request.getParameter("quality"));
		bean.setQuantity(quality);
		Double price=Double.parseDouble(request.getParameter("price"));
		bean.setPrice(price);
		bean.setRemarks(request.getParameter("remarks"));
		PosService posService=new PosService();
		String result=posService.addRecord(bean);
		return result;	
	}
	public PosBean viewRecord(HttpServletRequest request) {
		PosBean bean=new PosBean();
		PosService posService=new PosService();
		String date=request.getParameter("date");
		Date ogdate=Date.valueOf(date);
		bean=posService.viewRecord(request.getParameter("customer"),ogdate);
		return bean;
		
		
	} 
	public List<PosBean> viewAllRecords(HttpServletRequest request) {
		List<PosBean> list=new ArrayList<PosBean>();
		PosService posService=new PosService();
		list=posService.viewAllRecords();
		return list;
		
		
	}


        
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("action");
		try {
		if("newRecord".equalsIgnoreCase(operation)) {
			String result=addRecord(request);
			System.out.print(result);
			if(result.equalsIgnoreCase("success")) {
				response.sendRedirect("success.html");
			}else {
				response.sendRedirect("error.html");
			}
		}
		if("ViewRecord".equalsIgnoreCase(operation)) {
			PosBean bean=new PosBean();
			bean=viewRecord(request);
			if(bean==null) {
				request.setAttribute("message", "No matching records");
				request.getRequestDispatcher("displayTransaction.jsp").forward(request, response);
			}else {
				request.setAttribute("bean", bean);
				request.getRequestDispatcher("displayTransaction.jsp").forward(request, response);
			}
			
		}
		
		if("viewAllRecords".equalsIgnoreCase(operation)) {
			List<PosBean> list=new ArrayList<PosBean>();
			list=viewAllRecords(request);
			if(list==null) {
				request.setAttribute("message", "No records available");
				request.getRequestDispatcher("displayAllTransaction.jsp").forward(request, response);
			}else {
				request.setAttribute("bean", list);
				request.getRequestDispatcher("displayAllTransaction.jsp").forward(request, response);
			}	
			
		}
		
		}catch(Exception e) {
			response.sendRedirect("error.html");
		}
	}

}
