package com.wipro.pos.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import com.wipro.pos.bean.PosBean;
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

        
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("action");
		try {
		if("newRecord".equalsIgnoreCase(operation)) {
			String result=addRecord(request);
			if(result.equalsIgnoreCase("success")) {
				response.sendRedirect("success.html");
			}
		}		
		}catch(Exception e) {
			response.sendRedirect("error.html");
		}
	}

}
