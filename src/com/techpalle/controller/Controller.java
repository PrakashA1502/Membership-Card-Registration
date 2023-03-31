package com.techpalle.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.dao.AdminDao;
import com.techpalle.dao.CustomerDao;
import com.techpalle.model.Admin;
import com.techpalle.model.Customer;


@WebServlet("/")
public class Controller extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path =request.getServletPath();
		
		switch(path) 
		  { 
		  case"/delete":
		  {
			deleteCustomer(request,response);
			break;
		  }
		  case"/edit":
		  {
			editCustomer(request,response);
			break;
		  }
		  case"/editform":
		  {
			getEditForm(request,response);
			break;
		  }
		  case"/add":
		  {
			insertForm(request,response);
			break;
		  }
		  case"/addCustomer":
		  {
			addCustomer(request,response);
			break;
		  }
		  case"/startup":
		  {
			  getStartUpPage(request,response);
			  break;
		  }
		  case"/adminValide":
		  {
			  adminValidePage(request,response);
			  break;
		  }
		  case"/logout":
		  {
			 // adminHomePage(request,response);
			  System.exit(0);
			 // adminLogOut(request,response);
			  break;
		  }
		  default:
		  {
			  adminHomePage(request,response);
			  break;
		  }
		}
		
	}
	
	private void adminValidePage(HttpServletRequest request, HttpServletResponse response) 
	{
		//Read the Username and password from (Admin_login.jsp) page
		String n1 =request.getParameter("tbName");
		String p1 =request.getParameter("tbPass");
		
		
		Admin a1 = new Admin(n1,p1);
		
		//call the Dao method to validate admin
		boolean b1 =AdminDao.adminValide(a1);
		
		//Condition and Redirect user to destination page(Customer_list.jsp)
		if(b1 == true) 
		{
			getStartUpPage(request,response);
		}
		else
		{
			adminHomePage(request,response);
		}
		
	}


	private void adminHomePage(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
		RequestDispatcher rd=request.getRequestDispatcher("Admin_login.jsp");
		rd.forward(request, response);
	    } 
	    catch (ServletException | IOException e) 
	    {
		e.printStackTrace();
	    }
		
	}


	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		//Read the id from customer_list page url
		int i =Integer.parseInt(request.getParameter("id"));
		
		//call the DAO method to delete the row in database
		CustomerDao.deleteCustomer(i);
		
	    //Redirect user to customer_list page
		try 
		{
		response.sendRedirect("startup");
		} 
		catch (IOException e) 
		{
		e.printStackTrace();
		}
	}


	private void editCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		int id =Integer.parseInt(request.getParameter("tbId"));
		String name =request.getParameter("tbName");
		String email =request.getParameter("tbEmail");
		long mobile =Long.parseLong(request.getParameter("tbMobile"));
		
		Customer c1= new Customer(id,name,email,mobile);
		
		CustomerDao.editCustomer(c1);
		
		//getStartUpPage(request,response);
		
		//Redirect user to customer_list page
		try 
		{
			response.sendRedirect("startup");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	private void getEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		//Fetch the id from URL
		int i =Integer.parseInt(request.getParameter("id"));
		
		Customer cus =CustomerDao.getOneCustomer(i);
		
		try
		{
			RequestDispatcher rd =request.getRequestDispatcher("Customer_form.jsp");
			request.setAttribute("customer", cus);
			rd.forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}


	private void insertForm(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
		RequestDispatcher rd=request.getRequestDispatcher("Customer_form.jsp");
		rd.forward(request, response);
	    } 
	    catch (ServletException | IOException e) 
	    {
		e.printStackTrace();
	    }
		
	}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		//Reading data from Customer.form page
		String n =request.getParameter("tbName");
		String e =request.getParameter("tbEmail");
		long m =Long.parseLong(request.getParameter("tbMobile"));
		
		//Store the admin given data into model/object 
		Customer cus1 = new Customer(n,e,m);
		
		//Insert customer data to Database
		CustomerDao.addCustomer(cus1);
		
		//Redirect to customer.list page
		getStartUpPage(request,response);
		
		
	} 
	


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response)
	{
		
		try
		{
			ArrayList<Customer> alCustomer = CustomerDao.getAllCustomers();
			
			RequestDispatcher rd=request.getRequestDispatcher("Customer_list.jsp");
			request.setAttribute("al", alCustomer);
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
