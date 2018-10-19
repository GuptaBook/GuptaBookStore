package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.thirdware.guptabookstore.connectionprovider.ConnectionProvider;
import com.thirdware.guptabookstore.dao.CartDao;
import com.thirdware.guptabookstore.models.Cart;

public class CartDaoImpl implements CartDao {

	ConnectionProvider databaseConnection = new ConnectionProvider();
	Connection con = databaseConnection.CONN();

	@Override
	public int addCart(Cart cart) {

		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
		} else {
			try {
				String query = "insert into cart values(?,?,?,?,?)";
				PreparedStatement psmt = con.prepareStatement(query);
				cart = new Cart();
				psmt.setInt(2, cart.getBookId());
				psmt.setString(3, cart.getBookName());
				psmt.setInt(4, cart.getCustomerId());
				psmt.setInt(5, cart.getQuantity());
				psmt.setFloat(6, cart.getPrice());
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

	@Override
	public List<Cart> getCart() {
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
			return null;
		} else {
			try {
				String query = "select * from cart";
				PreparedStatement psmt = con.prepareStatement(query);
				ResultSet result = psmt.executeQuery();
				List<Cart> ls = new ArrayList<>();
				while (result.next()) {
					Cart cart = new Cart();
					cart.setCartId(result.getInt(1));
					cart.setBookId(result.getInt(2));
					cart.setBookName(result.getString(4));
					cart.setCustomerId(result.getInt(5));
					cart.setQuantity(result.getInt(6));
					cart.setPrice(result.getFloat(7));
					ls.add(cart);
				}
				result.close();
				psmt.close();
				con.close();
				return ls;

			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return null;
	}

	@Override
	public int deleteCart(Integer id) {
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
		} else {
			try {
				String query = "delete * from cart where id=?";
				PreparedStatement psmt = con.prepareStatement(query);
				int i = psmt.executeUpdate();
				if (i > 0) {
					System.out.println("Deleted Successfully");

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

	public int findMaxId() {
		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
		} else {
			try {
				String query = "select max(userid) from usertable";
				PreparedStatement psmt = con.prepareStatement(query);
				ResultSet result = psmt.executeQuery();
				int maxUserId = 0;
				while (result.next()) {
					maxUserId = result.getInt(1);
				}
				result.close();
				psmt.close();
				con.close();
				return maxUserId;

			}

			catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		return 0;
	}

}
