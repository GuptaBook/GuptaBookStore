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

import com.thirdware.guptabookstore.dao.CartDao;
import com.thirdware.guptabookstore.daoimpl.CartDaoImpl;
import com.thirdware.guptabookstore.models.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		CartDao cartDao = new CartDaoImpl();
		List<Cart> cartList =cartDao.getCart();
//		for(Cart userTable:userList){
//			out.println("<html><body>");
//			out.println("<h3> "+userTable.getUsername()+ "<h3>");
//			out.println("</body></html");
//
//		}
		request.setAttribute("cartList", cartList);
		RequestDispatcher rd = request.getRequestDispatcher("views/cart/Cart.jsp");
		rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId=Integer.parseInt(request.getParameter("bookid"));
		String bookName=request.getParameter("bookname");
		int customerId=Integer.parseInt(request.getParameter("customerid"));
		int quantity=Integer.parseInt(request.getParameter("quantity"));
		float price=Float.parseFloat(request.getParameter("price"));

		
		//.out.println(username+""+password+""+phoneno+""+emailid+"");
		
		Cart cart=new Cart();
		cart.setBookId(bookId);
		cart.setBookName(bookName);
		cart.setCustomerId(customerId);
		cart.setQuantity(quantity);
		cart.setPrice(price);
    
		CartDao cartDao=new CartDaoImpl();
		cartDao.addCart(cart);
		doGet(request, response);
      	}

}
