package com.thirdware.guptabookstore.dao;

import java.util.List;

import com.thirdware.guptabookstore.model.Customer;

public interface CustomerDao {
	public Customer  customerRegister(Customer customer);
	public Customer customerLogin(String email);
	
}
