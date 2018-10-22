package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.daoimpl.CustomerDaoImpl;
import com.thirdware.guptabookstore.model.Customer;

/**
 * Servlet implementation class CustomerRegistration
 */
@WebServlet("/CustomerRegistration")
public class CustomerRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rootContext = request.getServletContext().getRealPath("/");
		int cid=Integer.parseInt(request.getParameter("cid"));
		String cname=request.getParameter("cname");
		String email=request.getParameter("email");
		String phoneno=request.getParameter("phoneno");
		String role=request.getParameter("role");
		String password=request.getParameter("password");
		
		Customer customer=new Customer();
		customer.setCid(cid);
		customer.setCname(cname);
		customer.setEmail(email);
		customer.setPhoneno(phoneno);
		customer.setRole(role);
		customer.setPassword(password);
		
		CustomerDao customerDao=new CustomerDaoImpl();
		Customer c=customerDao.customerRegister(customer);
		System.out.println("Registered Successfully");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
	}

		
	}


