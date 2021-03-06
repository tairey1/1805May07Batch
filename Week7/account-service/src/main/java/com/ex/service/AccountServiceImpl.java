package com.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex.entities.Account;
import com.ex.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accRepo;

	@Override
	public List<Account> findAll() {
		return accRepo.findAll();
	}

	@Override
	public Account findById(int id) {
		return accRepo.findOne(id);
	}

	@Override
	public List<Account> findByCustomer(int id) {
		return accRepo.findByCustomerId(id);
	}

	@Override
	public Account add(Account acc) {
		return accRepo.save(acc);
	}

}
