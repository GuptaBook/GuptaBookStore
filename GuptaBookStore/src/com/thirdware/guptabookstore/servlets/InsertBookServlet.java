package com.thirdware.guptabookstore.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirdware.guptabookstore.dao.BookDao;
import com.thirdware.guptabookstore.daoimpl.BookDaoImpl;
import com.thirdware.guptabookstore.models.Book;

/**
 * Servlet implementation class InsertBookServlet
 */
@WebServlet("/InsertBookServlet")
public class InsertBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String rootContext = request.getServletContext().getRealPath("/");*/
		String bookname=request.getParameter("bookname");
		String bookdesc=request.getParameter("bookdesc");
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		float price=Float.parseFloat(request.getParameter("price"));
		int subid=Integer.parseInt(request.getParameter("subid"));
		int authid=Integer.parseInt(request.getParameter("authid"));
		Book book=new Book();
		book.setBookname(bookname);
		book.setBookdesc(bookdesc);
		book.setQuantity(quantity);
		book.setPrice(price);
		book.setSubid(subid);
		book.setAuthid(authid);
		BookDao bookDao=new BookDaoImpl();
		Book b=bookDao.insetBook(book);
		
		System.out.println("Inserted Successfully");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub\
		PrintWriter out=response.getWriter();
		doGet(request, response);
	}

}
