package com.techpalle.dao;

import java.sql.*;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class CustomerDao 
{
   private static final String dburl ="jdbc:mysql://localhost:3306/membershipCustomer_management";
   private static final String dbusername ="root";
   private static final String dbpassword ="PrakashA4$";
   
   private static Connection con = null;
   private static PreparedStatement ps = null;
   private static Statement stm = null;
   private static ResultSet rs = null;
   
   private static final String customersListQuery ="select * from customer";
   private static final String customerInsert = "insert into customer(name,email,mobile)values(?,?,?)";
   private static final String customerEditQuery ="select * from customer where cid=? ";
   private static final String customerUpdate ="update customer set name=?,email=?,mobile=? where cid=?";
   private static final String customerDelete ="delete from customer where cid=?";
  // private static final String adminValideQuery="select aname,apassword from admin where aname=? and apassword=?";
   
  /* public static boolean adminValide(Admin ad) 
   {
	 
	   boolean b = false;
	 try
	 { 
	    con = getConnectionDef();
		ps = con.prepareStatement(adminValideQuery);
		ps.setString(1, ad.getName());
		ps.setString(2, ad.getPassword());
		
		rs =ps.executeQuery();
		
		b =rs.next();
		
		String n =rs.getString("name");
		String p =rs.getString("pass");
		
		if(n .equals(ad.getName()) && (p.equals(ad.getPassword()))) 
		{
			b = true;
		}
	 }
	 catch (SQLException e) 
	 {
		e.printStackTrace();
	 }
	return b;
   }*/
   
   public static void deleteCustomer(int id) 
   {
	  
	   try 
	   {
		   con =getConnectionDef();
		   ps=con.prepareStatement(customerDelete);
		   ps.setInt(1, id);
		   
		   ps.executeUpdate();
	   } 
	   catch (SQLException e)
	   {
		e.printStackTrace();
	   }
	   finally 
		  {
			  try
			  {
				  if(ps != null) 
				  {
					ps.close();
				  }
				  if(con != null) 
				  {
					con.close();
				  }
			   }
				  catch (SQLException e) 
				  {
					e.printStackTrace();
				  }
	      }
   }
   
   public static void editCustomer(Customer c) 
   {
	   
	   try
	   {
		   con= getConnectionDef();
		   ps= con.prepareStatement(customerUpdate);
		   ps.setString(1, c.getName());
		   ps.setString(2, c.getEmail());
		   ps.setLong(3, c.getMobile());
		   ps.setInt(4, c.getId());
		   
		   ps.executeUpdate();
	   }
	   catch (SQLException e) 
	   {
		e.printStackTrace();
	   }
	   finally 
		  {
			  try
			  {
				  if(ps != null) 
				  {
					ps.close();
				  }
				  if(con != null) 
				  {
					con.close();
				  }
			   }
				  catch (SQLException e) 
				  {
					e.printStackTrace();
				  }
	      }
   }
   
   public static Customer getOneCustomer(int id) 
   {
	   Customer c1 = null;
	   try 
	   {
		   con = getConnectionDef();
		   ps=con.prepareStatement(customerEditQuery);
		   ps.setInt(1, id);
		   
		   rs = ps.executeQuery();
		   
		   rs.next();
		   
		   int i =rs.getInt("cid");
		   String n =rs.getString("name");
		   String e =rs.getString("email");
		   long m =rs.getLong("mobile");
		   
		   c1 = new Customer(i,n,e,m);
	   } 
	   catch (SQLException e) 
	   {
		e.printStackTrace();
	   }
	   finally 
		  {
			  try
			  {
				  if(rs != null) 
				  {
					rs.close();
				  }
				  if(ps != null) 
				  {
					ps.close();
				  }
				  if(con != null) 
				  {
					con.close();
				  }
			   }
				  catch (SQLException e) 
				  {
					e.printStackTrace();
				  }
			  }
	return c1;
   }
   
   
   public static void addCustomer(Customer cus) 
   {
	 
	 try
	 {
		 con = getConnectionDef();
		 ps=con.prepareStatement(customerInsert);
		 ps.setString(1, cus.getName());
		 ps.setString(2, cus.getEmail());
		 ps.setLong(3, cus.getMobile());
		 
		 ps.executeUpdate();
	 } 
	 catch (SQLException e) 
	 {
		e.printStackTrace();
	 }
	 finally 
	  {
		  try
		  {
			  if(ps != null) 
			  {
				ps.close();
			  }
			  if(con != null) 
			  {
				con.close();
			  }
		   }
			  catch (SQLException e) 
			  {
				e.printStackTrace();
			  }
		  }
   }
   
   
   public static Connection getConnectionDef() 
   {
	   try
	   {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(dburl,dbusername,dbpassword);
	   } 
	   catch (ClassNotFoundException e)
	   {
		e.printStackTrace();
	   } 
	   catch (SQLException e)
	   {
		e.printStackTrace();
	   }
	return con;
   }
   
   public static ArrayList<Customer> getAllCustomers() 
   {
	   ArrayList<Customer> al = new ArrayList<Customer>();
	   
	  try 
	  {
		con = getConnectionDef();
		stm=con.createStatement();
		
		rs = stm.executeQuery(customersListQuery);
		
		while(rs.next()) 
		{
			int i = rs.getInt("cid");
			String n = rs.getString("name");
			String e = rs.getString("email");
			long m =rs.getLong("mobile");
			
			Customer c = new Customer(i,n,e,m);
			
			al.add(c);
		}
	  } 
	  catch (SQLException e) 
	  {
		e.printStackTrace();
	  }
	  finally 
	  {
		  try
		  {
			  if(rs != null) 
			  {
				rs.close();
			  }
			  if(stm != null) 
			  {
				stm.close();
			  }
			  if(con != null) 
			  {
				con.close();
			  }
		   }
			  catch (SQLException e) 
			  {
				e.printStackTrace();
			  }
		  }
	  
	return al;
   }
}
