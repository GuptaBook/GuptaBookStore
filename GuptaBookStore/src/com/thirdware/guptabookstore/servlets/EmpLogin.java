package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.dao.EmpDao;
import com.thirdware.guptabookstore.daoimpl.CustomerDaoImpl;
import com.thirdware.guptabookstore.daoimpl.EmpDaoImpl;
import com.thirdware.guptabookstore.model.Customer;
import com.thirdware.guptabookstore.model.Emp;

/**
 * Servlet implementation class EmpLogin
 */
@WebServlet("/EmpLogin")
public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpLogin() {
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
		
		Emp emp=new Emp();
		
		emp.setEmail(email);
		emp.setPassword(password);
		
		EmpDao empDao=new EmpDaoImpl();
		Emp e=empDao.empLogin(email);
		if(e==null)
		{
			System.out.println("email doesn't exists");
		}
		else if(password.equals(e.getPassword()))
		{
			System.out.println("Logged in");
			HttpSession session=request.getSession();  
			session.setAttribute("employee name",emp.getEname());
			session.setAttribute("role",emp.getRole());
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
		
	}

	}


