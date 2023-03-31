package com.techpalle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.techpalle.model.Admin;

public class AdminDao 
{
     
	   private static final String dburl ="jdbc:mysql://localhost:3306/membershipCustomer_management";
	   private static final String dbusername ="root";
	   private static final String dbpassword ="PrakashA4$";
	   
	   private static Connection con = null;
	   private static PreparedStatement ps = null;
	   private static ResultSet rs = null;
	   
	   private static final String adminValideQuery="select aname,apassword from admin where aname=? and apassword=?";
	   
	   public static boolean adminValide(Admin ad) 
	   {
		 
		   boolean b = false;
		 try
		 { 
		    Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection(dburl,dbusername,dbpassword);
			ps = con.prepareStatement(adminValideQuery);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getPassword());
			
			rs =ps.executeQuery();
			
			b =rs.next();
			
		 /* String n =rs.getString("name");
			String p =rs.getString("pass");
			
			if(n .equals(ad.getName()) && (p.equals(ad.getPassword()))) 
			{
				b = true;
			}*/
		 }
		 catch (SQLException e) 
		 {
			e.printStackTrace();
		 }
		 catch (ClassNotFoundException e)
		 {
			e.printStackTrace();
		}
		return b;
	   }
}
