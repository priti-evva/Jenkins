package com.capgemini.test;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientOpeningAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImplementation;

public class AccountTest{
		

		//public static final Account account = null;

		@Mock
		AccountService accountService;
		
		@Mock
		AccountRepository accountRepository;
		
		AccountServiceImplementation a;
		@Before
		public void setUp() throws Exception {
			MockitoAnnotations.initMocks(this);
			
			accountService = new AccountServiceImplementation(accountRepository);
		}
		
		@Test//(expected=InsufficientOpeningAmountException.class)
		public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientOpeningAmountException
		{
			accountService.createAccount(101, 400);
		}
		
		@Test
		public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningAmountException
		{
			Account account =new Account(101,5000);
			
			when(accountRepository.save(account)).thenReturn(true);
			assertEquals(account, accountService.createAccount(101, 5000));
		}


}
