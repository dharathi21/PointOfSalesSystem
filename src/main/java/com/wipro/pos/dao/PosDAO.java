package com.wipro.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.pos.bean.PosBean;
import com.wipro.pos.util.DButil;

public class PosDAO {
	public String createRecord(PosBean bean) {
		Connection con;
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
			int success=ps.executeUpdate();	
		}catch(SQLException e) {
			return "Fail";			
		} return bean.getTransId();
	}
	
	
}
