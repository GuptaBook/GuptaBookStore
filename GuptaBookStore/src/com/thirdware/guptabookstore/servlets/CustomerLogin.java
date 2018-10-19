package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.daoimpl.CustomerDaoImpl;
import com.thirdware.guptabookstore.model.Customer;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
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
		
		String email=request.getParameter("email");
	    String password=request.getParameter("password");
		
		Customer customer=new Customer();
		
		customer.setEmail(email);
		customer.setPassword(password);
		
		CustomerDao customerDao=new CustomerDaoImpl();
		Customer c=customerDao.customerLogin(email);
		if(c==null)
		{
			System.out.println("email doesn't exists");
		}
		else if(password.equals(c.getPassword()))
			{
				System.out.println("Logged in");
				HttpSession session=request.getSession();  
				session.setAttribute("username",customer.getCname());
				session.setAttribute("role",customer.getRole());
			}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
		
	}
}
