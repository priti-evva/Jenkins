package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImplementation implements AccountService {
	AccountRepository accountRepository;


	public AccountServiceImplementation(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
		
	}

	public Account createAccount(int accountNumber, int amount) throws InsufficientOpeningAmountException {
		
		if(amount<500)
		{
			throw new InsufficientOpeningAmountException();
		}
		Account account = new Account(accountNumber,amount);
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
		return null;
	}
	

	@Override
	public Account withdraw(int accountNumber, int amount)throws InvalidAccountNumberException, InsufficientBalanceException {
		 Account account=new Account(accountNumber,amount);
			 
		
		 if(account.getAmount()-amount<0||accountRepository.searchAccount(accountNumber)!=account)
			{
			 InvalidAccountNumberException ex=new InvalidAccountNumberException();
			 InsufficientBalanceException ex1=new InsufficientBalanceException();
			 ex1.addSuppressed(ex);
			 throw ex1;
			 }
		account.setAmount(account.getAmount()-amount);
	    return account;
						
					
				 
		
					/*
					 * if(accountRepository.searchAccount(accountNumber)!=account) {
					 * 
					 * throw new InvalidAccountNumberException(); }
					 */
		 //account.setAmount(account.getAmount()-amount);
			//return account;
			
		
		}
		
	
	
	@Override
	public Account amountDeposit(int accountNumber, int amount) throws InvalidAccountNumberException 
	{
		
		Account account=new Account(accountNumber,amount);
	
		if(accountRepository.searchAccount(accountNumber)== account) 
		{
			
			account.setAmount(account.getAmount()+amount);
			return account;
			
		}
		 throw new InvalidAccountNumberException();
		
}
	
	
}



