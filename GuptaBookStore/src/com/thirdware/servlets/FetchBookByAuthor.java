package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirdware.guptabookstore.dao.AuthorDao;
import com.thirdware.guptabookstore.daoimpl.AuthorDaoImpl;
import com.thirdware.guptabookstore.models.Book;

/**
 * Servlet implementation class FetchBookByAuthor
 */
@WebServlet("/FetchBookByAuthor")
public class FetchBookByAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBookByAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
AuthorDao fetch = new AuthorDaoImpl();
		
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		List<Book> bookData = fetch.getAuthor(id);
		
		/*System.out.println(bookData);
		for(Book book : bookData){
			System.out.println(book.getBookid()+" " + book.getBookname()+" "+book.getBookdesc());
		}*/
		
    	request.setAttribute("booksList", bookData);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/category/subject.jsp");
		rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
