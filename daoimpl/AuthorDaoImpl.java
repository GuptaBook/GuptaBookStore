package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thirdware.guptabookstore.connectionprovider.ConnectionProvider;
import com.thirdware.guptabookstore.dao.AuthorDao;
import com.thirdware.guptabookstore.models.Author;
import com.thirdware.guptabookstore.models.Book;

public class AuthorDaoImpl implements AuthorDao {

	public int insertAuthor(Author author) {
		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
		} else {
			try {
				String query = "insert into author values(?,?,?)";
				PreparedStatement psmt = con.prepareStatement(query);
                System.out.println("inside the insert method on daoimpl "+(getMaxId()+1));
				psmt.setInt(1, getMaxId() + 1);
				psmt.setString(2, author.getAuthorname());
				psmt.setString(3, author.getAuthordescription());

				int i = psmt.executeUpdate();
				if (i > 0) {
					System.out.println("Inserted Successfully");

				}
				psmt.close();
				con.close();

			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return 0;
	}

	public List<Book> getAuthor(int id) {

		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check");
		} else {
			String query = "select * from book where authid = ? and bookstatus=1";
			try {
				List<Book> ls=new ArrayList<>();
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setInt(1, id);
				ResultSet result = psmt.executeQuery();
				while (result.next()) {
					System.out.println(result.getInt("bookid")+" "+result.getInt(1) +" "+result.getInt(7) );
					Book book=new Book();
					book.setBookid(result.getInt("bookid"));
					book.setBookname(result.getString(2));
					book.setBookdesc(result.getString(3));
					book.setQuantity(result.getInt(4));
					book.setPrice(result.getFloat(5));
					book.setSubid(result.getInt(6));
					book.setAuthid(result.getInt(7));
					book.setBookstatus(result.getInt(8));
					ls.add(book);
				}
				return ls;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	  


	private int getMaxId() {
			// TODO Auto-generated method stub
		System.out.println("getmaxid method is entering");
			int maxId=0;
			String query="select max(authorid) from author";
			try{
				ConnectionProvider databaseConnection = new ConnectionProvider();
				Connection con = databaseConnection.CONN();
				if(con==null)
					System.out.println("please check the connection");
				PreparedStatement st=con.prepareStatement(query);
				ResultSet rs=st.executeQuery();
				while(rs.next()){
					maxId=rs.getInt(1);
				}
				System.out.println("maxid "+maxId);
				return maxId;
			}catch(Exception e){System.out.println(e);}
			return -1;
		}

}
