package com.iiht.training.eloan.service;

import java.util.List;

import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;

public interface ClerkService {
	
	public List<LoanOutputDto> allAppliedLoans();
		
	public ProcessingDto processLoan(Long clerkId,
									 Long loanAppId,
									 ProcessingDto processingDto);
}
