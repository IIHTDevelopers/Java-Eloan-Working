package com.iiht.training.eloan.service;

import java.util.List;

import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.UserDto;

public interface CustomerService {
	
	public UserDto register(UserDto userDto);
	
	public LoanOutputDto applyLoan(Long customerId,
								   LoanDto loanDto);
	
	public LoanOutputDto getStatus(Long loanAppId);
		
	public List<LoanOutputDto> getStatusAll(Long customerId);
}
