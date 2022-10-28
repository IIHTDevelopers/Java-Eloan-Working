package com.iiht.training.eloan.service;

import java.util.List;

import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.RejectDto;
import com.iiht.training.eloan.model.SanctionDto;
import com.iiht.training.eloan.model.SanctionOutputDto;

public interface ManagerService {
	
	public List<LoanOutputDto> allProcessedLoans();
	
	public RejectDto rejectLoan(Long managerId,
								Long loanAppId,
								RejectDto rejectDto);
	
	
	public SanctionOutputDto sanctionLoan(Long managerId,
										  Long loanAppId,
										  SanctionDto sanctionDto);
}
