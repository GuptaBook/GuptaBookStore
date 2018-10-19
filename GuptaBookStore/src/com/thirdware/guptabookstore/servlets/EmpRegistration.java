package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.dao.EmpDao;
import com.thirdware.guptabookstore.daoimpl.CustomerDaoImpl;
import com.thirdware.guptabookstore.daoimpl.EmpDaoImpl;
import com.thirdware.guptabookstore.model.Customer;
import com.thirdware.guptabookstore.model.Emp;

/**
 * Servlet implementation class EmpRegistration
 */
@WebServlet("/EmpRegistration")
public class EmpRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpRegistration() {
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
		int eid=Integer.parseInt(request.getParameter("eid"));
		String ename=request.getParameter("ename");
		String email=request.getParameter("email");
		String phoneno=request.getParameter("phoneno");
		String role=request.getParameter("role");
		String password=request.getParameter("password");
		
		Emp emp=new Emp();
		emp.setEid(eid);
		emp.setEname(ename);
		emp.setEmail(email);
		emp.setPhoneno(phoneno);
		emp.setRole(role);
		emp.setPassword(password);
		
		EmpDao empDao=new EmpDaoImpl();
		Emp e=empDao.empRegister(emp);
		System.out.println("Registered Successfully");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
	}

}
