package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.collections.SynchronizedStack;

import com.thirdware.guptabookstore.dao.AuthorDao;
import com.thirdware.guptabookstore.daoimpl.AuthorDaoImpl;
import com.thirdware.guptabookstore.models.Author;
import com.thirdware.guptabookstore.models.Book;

/**
 * Servlet implementation class AuthorServlet
 */
@WebServlet("/AuthorServlet")
public class AuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		AuthorDao fetch = new AuthorDaoImpl();
		//int id=Integer.parseInt(request.getParameter("id"));
		
		int id=1;
		System.out.println("inserted the method id:"+id);
		List<Book> bookData = fetch.getAuthor(id);
		System.out.println(bookData);
		for(Book book : bookData){
			System.out.println(book.getBookid()+" " + book.getBookname()+" "+book.getBookdesc());
		}
		
    	request.setAttribute("booksList", bookData);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/category/author.jsp");
		rd.forward(request, response);
		
		response.getWriter().append("Served at:").append(request.getContextPath());
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int authorid=Integer.parseInt(request.getParameter("authorid"));
		String authorname=request.getParameter("authname");
		String authordescription=request.getParameter("authdesc");
		
		//System.out.println(authorid+" "+authorname+" "+authordescription+" ");
		
		Author author = new Author();
		//author.setAuthorid(authorid);
		author.setAuthorname(authorname);
		author.setAuthordescription(authordescription);
    
		AuthorDao insert=new AuthorDaoImpl();
		System.out.println("inserted method in servlet "+author);
		insert.insertAuthor(author);
		System.out.println("inserted to db");
		doGet(request, response);
	}
		


}
