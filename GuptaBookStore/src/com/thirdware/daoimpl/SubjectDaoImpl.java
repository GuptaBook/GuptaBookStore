package com.thirdware.guptabookstore.daoimpl;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.thirdware.guptabookstore.connectionprovider.ConnectionProvider;
import com.thirdware.guptabookstore.models.Subject;

public class SubjectDaoImpl {
	public int insertSubject(Subject subject) {
		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
		} else {
			try {
				String query = "insert into subject values(?,?,?,?,?)";
				PreparedStatement psmt = con.prepareStatement(query);
				subject = new Subject();

				psmt.setInt(1, 1);
				psmt.setString(2, subject.getSubname());
				psmt.setString(3, subject.getSubdescription());

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

	public List<Book> getSubject(int id) {

		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not connected, Please check");
		} else {
			String query = "select * from book where subid = ?";
			try {
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setInt(1, id);
				ResultSet result = psmt.executeQuery();
				while (result.next()) {
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
