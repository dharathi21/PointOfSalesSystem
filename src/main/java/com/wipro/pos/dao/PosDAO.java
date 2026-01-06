package com.wipro.pos.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.wipro.pos.bean.PosBean;
import com.wipro.pos.util.DButil;

public class PosDAO {
	Connection con;
	public String createRecord(PosBean bean) {
		
		try {
			con=DButil.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into pos_tb values(?,?,?,?,?,?,?,?)");
			ps.setString(1, bean.getTransId());
			ps.setString(2, bean.getCustomerName());
			ps.setString(3, bean.getItemName());
			ps.setDate(4, bean.getTransDate());
			ps.setInt(5, bean.getQuantity());
			ps.setDouble(6,bean.getPrice());
			ps.setDouble(7, bean.getTotalAmount());
			ps.setString(8, bean.getRemarks());
			ps.executeUpdate();	
		}catch(SQLException e) {
			return "Fail";			
		} return bean.getTransId();
	}
	
	public String generateTransID(String customerName, Date transDate) {
		String id="";
		try {
			DateFormat format=new SimpleDateFormat("yyyyMMdd");
			String datepart=format.format(transDate);
			
			String namepart=customerName.substring(0,2).toUpperCase();
				con=DButil.getConnection();
				PreparedStatement ps=con.prepareStatement("select pos_seq from Dual");
				ResultSet rs=ps.executeQuery();
				int seq=0;
				if(rs.next()) {
					seq=rs.getInt(1);
				}
				String seqpart=String.format("%02d", seq);
			 id=datepart+namepart+seqpart;
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return id;	
			}

	public boolean recordExists(String customerName, Date transDate) {
		try {
			con=DButil.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from pos_tb where customername=? and trans_date=?");
			ps.setString(1,customerName);
			ps.setDate(2, transDate);
			ResultSet success=ps.executeQuery();
			if(success.next()) {
				return true;
			}
			
		}catch(SQLException e) {
			    e.printStackTrace();
		} 
		return false;
	}
	
	
	
}
