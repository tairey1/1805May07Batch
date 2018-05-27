package com.ex.dao;

import java.util.ArrayList;
import java.util.List;

public interface UserDao<T> {
	
	List<T> getAllCustomers();
	T addCustomer(T obj);
	boolean isUniqueUsername(T obj);
	boolean isUniquePassword(T obj);
	void depositMoney(T obj);
	void multiAccount(T obj);
}
