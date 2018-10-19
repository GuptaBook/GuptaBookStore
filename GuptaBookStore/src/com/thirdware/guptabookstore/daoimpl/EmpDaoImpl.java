package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.thirdware.guptabookstore.connectionprovider.ConnectionProvider;
import com.thirdware.guptabookstore.dao.EmpDao;
import com.thirdware.guptabookstore.model.Emp;

public class EmpDaoImpl implements EmpDao {
	public Emp empRegister(Emp emp) {

		ConnectionProvider dbConnection=new ConnectionProvider();
		Connection con=dbConnection.CONN();
		if(con==null)
		{
			System.out.println("Not Connected,Try again");
		}
		else
		{
			String query="insert into emp values(?,?,?,?,?,?)";
			try
			{
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setInt(1, getMaxId()+1);
				pstmt.setString(2,emp.getEname());
				pstmt.setString(3,emp.getEmail());
				pstmt.setString(4,emp.getPhoneno());
				pstmt.setString(5,emp.getRole());
				pstmt.setString(6,emp.getPassword());
				int count=pstmt.executeUpdate();
				
				if(count>0)
				{
					System.out.println("Records affected:"+count);
				}
				if(con!=null)
					con.close();
				if(pstmt!=null)
					   pstmt.close();
				return emp;
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	
	}

	private int getMaxId() {
		
		int maxId=0;
		String query="select max(eid) from emp";
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
		}
	    catch(Exception e)
		{
	    	System.out.println(e);
	    	}
		return -1;
	}

	@Override
	public Emp empLogin(String email) {
	{
		ConnectionProvider dbConnection=new ConnectionProvider();
		Connection con=dbConnection.CONN();
		if(con==null)
		{
			System.out.println("Not Connected,Try again");
		}
		else
		{
			String query="select * from emp where email=?";
			try
			{
				PreparedStatement pstmt=con.prepareStatement(query);
			    Emp emp=new Emp();
				pstmt.setString(1,emp.getEmail());
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					emp.setEid(rs.getInt(1));
					emp.setEname(rs.getString(2));
					emp.setEmail(rs.getString(3));
					emp.setPhoneno(rs.getString(4));
					emp.setRole(rs.getString(5));
					emp.setPassword(rs.getString(5));
				}
				if(con!=null)
					con.close();
				if(pstmt!=null)
					   pstmt.close();
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	}
}




