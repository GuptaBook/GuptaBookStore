package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thirdware.guptabookstore.connnectionprovider.ConnectionProvider;
import com.thirdware.guptabookstore.dao.BookDao;
import com.thirdware.guptabookstore.models.Author;
import com.thirdware.guptabookstore.models.Book;
import com.thirdware.guptabookstore.models.Subject;

public class BookDaoImpl implements BookDao {
	ConnectionProvider connectionProvider = new ConnectionProvider();

	PreparedStatement psmt;

	@Override
	public Book insetBook(Book book) {
		// TODO Auto-generated method stub
		Connection con = connectionProvider.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check the connection!");
			return null;
		}
		try {
			String query = "insert into book(bookid,bookname,bookdesc,quantity,price,subid,authid) values(?,?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, getMaxId()+1);
			psmt.setString(2, book.getBookname());
			psmt.setString(3, book.getBookdesc());
			psmt.setInt(4, book.getQuantity());
			psmt.setFloat(5,book.getPrice());
			psmt.setInt(6, book.getSubid());
			psmt.setInt(7, book.getAuthid());
			psmt.executeUpdate();
			System.out.println("Inserted successfully");
			psmt.close();
			con.close();
			return book;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Book> fetchAllBook() {
		// TODO Auto-generated method stub
		Connection con = connectionProvider.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check the connection!");
			return null;
		}
		try {
			ArrayList<Book> ls=new ArrayList<>();
			String query = "select * from book where bookstatus=1";
			psmt=con.prepareStatement(query);
			ResultSet result=psmt.executeQuery();
			while(result.next()){
				Book b=new Book();
				b.setBookid(result.getInt(1));
				b.setBookname(result.getString(2));
				b.setBookdesc(result.getString(3));
				b.setQuantity(result.getInt(4));
				b.setPrice(result.getFloat(5));
				b.setSubid(result.getInt(6));
				b.setAuthid(result.getInt(7));
				ls.add(b);				
			}
			psmt.close();
			con.close();
			return ls;
			}catch(Exception e){
				System.out.println(e);
				}
		return null;
	}

	@Override
	public Book fetchBookBId(int bid) {
		// TODO Auto-generated method stub
		Connection con = connectionProvider.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check the connection!");
			return null;
		}
		try {
			Book b=new Book();
			String query = "select * from book where bookstatus=1 and bookid=?";
			psmt=con.prepareStatement(query);
			psmt.setInt(1, bid);
			ResultSet result=psmt.executeQuery();
			while(result.next()){				
				b.setBookid(result.getInt(1));
				b.setBookname(result.getString(2));
				b.setBookdesc(result.getString(3));
				b.setQuantity(result.getInt(4));
				b.setPrice(result.getFloat(5));
				b.setSubid(result.getInt(6));
				b.setAuthid(result.getInt(7));			
			}
			psmt.close();
			con.close();
			return b;
			}catch(Exception e){
				System.out.println(e);
				}
		return null;
	}

	@Override
	public List<Subject> fetchBookBySub() {
		// TODO Auto-generated method stub
		Connection con = connectionProvider.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check the connection!");
			return null;
		}
		try {
			List<Subject> ls=new ArrayList<>();
			String query = "select * from subject";
			psmt=con.prepareStatement(query);
			ResultSet result=psmt.executeQuery();
			while(result.next()){
				Subject sub=new Subject();
				sub.setSubid(result.getInt(1));
				sub.setSubname(result.getString(2));
				sub.setSubdescription(result.getString(3));
				ls.add(sub);
			}
			psmt.close();
			con.close();
			return ls;
		}catch(Exception e){
			System.out.println(e);
			}
		return null;
	}

	@Override
	public List<Author> fetchBookByAuth() {
		// TODO Auto-generated method stub
		Connection con = connectionProvider.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check the connection!");
			return null;
		}
		try {
			List<Author> ls=new ArrayList<>();
			String query = "select * from author";
			psmt=con.prepareStatement(query);
			ResultSet result=psmt.executeQuery();
			while(result.next()){
				Author auth=new Author();
				auth.setAuthid(result.getInt(1));
				auth.setAuthdesc(result.getString(2));
				auth.setAuthname(result.getString(3));
				ls.add(auth);
			}
			psmt.close();
			con.close();
			return ls;
		}catch(Exception e){
			System.out.println(e);
			}
		return null;
	}
	private int getMaxId() {
		// TODO Auto-generated method stub
		int maxId=0;
		String query="select max(userid) from usertable";
		try{
			ConnectionProvider databaseConnection = new ConnectionProvider();
			Connection con = databaseConnection.CONN();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				maxId=rs.getInt(1);
			}
			System.out.println(maxId);
			return maxId;
		}catch(Exception e){System.out.println(e);}
		return -1;
	}

	@Override
	public Book updateBook(Book book) {
		// TODO Auto-generated method stub
		Connection con = connectionProvider.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check the connection!");
			return null;
		}
		try {
			String query = "update book set bookname=?,bookdesc=?,quantity=?,price=?,subid=?,authid=?,bookstatus=0 where bookid=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, book.getBookname());
			psmt.setString(2, book.getBookdesc());
			psmt.setInt(3, book.getQuantity());
			psmt.setFloat(4,book.getPrice());
			psmt.setInt(5, book.getSubid());
			psmt.setInt(6, book.getAuthid());
			psmt.setInt(7, book.getBookid());
			psmt.executeUpdate();
			System.out.println("Updated successfully");
			psmt.close();
			con.close();
			return book;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
