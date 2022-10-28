package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.exception.AlreadyProcessedException;
import com.iiht.training.eloan.exception.ClerkNotFoundException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ClerkService;

@Service
public class ClerkServiceImpl implements ClerkService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository processingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public List<LoanOutputDto> allAppliedLoans() {
		List<Loan> appliedLoanList = loanRepository.findLoanByStatus(1);
		List<LoanOutputDto> loanDtos = new ArrayList<LoanOutputDto>();
		if(appliedLoanList.size() == 0) {
			return null;
		}
		else {
			for(Loan loan: appliedLoanList) {
				LoanDto loanDto = new LoanDto();
				loanDto.setBillingIndicator(loan.getBillingIndicator());
				loanDto.setBusinessStructure(loan.getBusinessStructure());
				loanDto.setLoanAmount(loan.getLoanAmount());
				loanDto.setLoanName(loan.getLoanName());
				loanDto.setLoanApplicationDate(loan.getLoanApplicationDate());
				Users user = usersRepository.findCustomerById("Customer",loan.getCustomerId());
				UserDto dto = new UserDto();
				dto.setId(user.getId());
				dto.setFirstName(user.getFirstName());
				dto.setLastName(user.getLastName());
				dto.setEmail(user.getEmail());
				dto.setMobile(user.getMobile());
				loanDto.setTaxIndicator(loan.getTaxIndicator());
				LoanOutputDto loanODto= new LoanOutputDto();
				loanODto.setCustomerId(loan.getCustomerId());
				loanODto.setLoanAppId(loan.getId());
				loanODto.setUserDto(dto);
				loanODto.setLoanDto(loanDto);
				loanODto.setProcessingDto(null);
				loanODto.setRemark("");
				loanODto.setSanctionOutputDto(null);
				loanODto.setStatus("applied");
				loanDtos.add(loanODto);
			}
		return loanDtos;
		}
	}

	@Override
	public ProcessingDto processLoan(Long clerkId, Long loanAppId, ProcessingDto processingDto) {
		Loan loan = loanRepository.findById(loanAppId).get();
		if(loan == null)
			throw new LoanNotFoundException("Loan Not found Exception");
		Users user = usersRepository.findCustomerById("Clerk", clerkId);
		if(user == null)
			throw new ClerkNotFoundException("Clerk not found with Id "+clerkId);
		Loan processedLoan = loanRepository.findLoanByStatusAndId(loanAppId, 2);
		if(processedLoan != null) 
			throw new AlreadyProcessedException("Loan Already processed for Loan Id "+loanAppId);
		else{
			ProcessingInfo pInfo = new ProcessingInfo();
			pInfo.setAcresOfLand(processingDto.getAcresOfLand());
			pInfo.setAddressOfProperty(processingDto.getAddressOfProperty());
			pInfo.setAppraisedBy(processingDto.getAppraisedBy());
			pInfo.setLandValue(processingDto.getLandValue());
			pInfo.setLoanAppId(loanAppId);
			pInfo.setLoanClerkId(clerkId);
			pInfo.setSuggestedAmountOfLoan(processingDto.getSuggestedAmountOfLoan());
			pInfo.setValuationDate(processingDto.getValuationDate());
			processingInfoRepository.save(pInfo);
			
			loan.setStatus(2);
			loanRepository.save(loan);
			return processingDto;
		}
		
	}

}
