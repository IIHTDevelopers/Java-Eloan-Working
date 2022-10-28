package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.SanctionInfo;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.model.SanctionOutputDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository processingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public UserDto register(UserDto userDto) {
		Users user = new Users();
		if(userDto.getId() != null) 
			user.setId(userDto.getId());
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setMobile(userDto.getMobile());
			user.setRole("Customer");
			usersRepository.save(user);
			if(user.getId() != null)
				userDto.setId(user.getId());
			return userDto;
	
	}

	@Override
	public LoanOutputDto applyLoan(Long customerId, LoanDto loanDto)  {
		
		Users user = usersRepository.findCustomerById("Customer",customerId);
		if(user == null) {
			return null;
		}
		else {
			LoanOutputDto loanODto= new LoanOutputDto();
			Loan loan = new Loan();
			loan.setCustomerId(customerId);
			loan.setBillingIndicator(loanDto.getBillingIndicator());
			loan.setBusinessStructure(loanDto.getBusinessStructure());
			loan.setLoanAmount(loanDto.getLoanAmount());
			loan.setLoanName(loanDto.getLoanName());
			loan.setLoanApplicationDate(loanDto.getLoanApplicationDate());
			loan.setTaxIndicator(loanDto.getTaxIndicator());
			loan.setStatus(1);
			loanRepository.save(loan);
			
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setEmail(user.getEmail());
			dto.setMobile(user.getMobile());
			loanODto.setCustomerId(loan.getCustomerId());
			loanODto.setLoanAppId(loan.getId());
			loanODto.setUserDto(dto);
			loanODto.setLoanDto(loanDto);
			loanODto.setProcessingDto(null);
			loanODto.setRemark("");
			loanODto.setSanctionOutputDto(null);
			loanODto.setStatus("applied");
			return loanODto;
		}
			
		
		
	}

	@Override
	public LoanOutputDto getStatus(Long loanAppId) {
		Loan loan = loanRepository.findById(loanAppId).get();
		if(loan == null)
			return null;
		else {
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
			if(loan.getStatus()==1)
			loanODto.setStatus("applied");
			else if(loan.getStatus()==2)
				loanODto.setStatus("processed");
			else if(loan.getStatus()==3)
				loanODto.setStatus("sanctioned");
			else if(loan.getStatus()==4)
				loanODto.setStatus("rejected");
			return loanODto;
		}
	}

	@Override
	public List<LoanOutputDto> getStatusAll(Long customerId) {
		List<Loan> loanList = loanRepository.findLoanByCustomerId(customerId);
		if(loanList.size() == 0) {
			Users user = usersRepository.findCustomerById("Customer",customerId);
				if(user == null)
					throw new CustomerNotFoundException("Customer with Id "+customerId+" not found");
			return null;
			
		}
			
		else {
			List<LoanOutputDto> loanOutputDtoList = new ArrayList<LoanOutputDto>();
			for(Loan loan: loanList) {
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
				if(loan.getStatus()==2) {
					ProcessingInfo processingInfo = processingInfoRepository.findByLoanId(loan.getId());
					if(processingInfo == null)
						loanODto.setProcessingDto(null);
					else {
						ProcessingDto pfDto = new ProcessingDto();
						pfDto.setAcresOfLand(processingInfo.getAcresOfLand());
						pfDto.setAddressOfProperty(processingInfo.getAddressOfProperty());
						pfDto.setAppraisedBy(processingInfo.getAppraisedBy());
						pfDto.setLandValue(processingInfo.getLandValue());
						pfDto.setSuggestedAmountOfLoan(processingInfo.getSuggestedAmountOfLoan());
						pfDto.setValuationDate(processingInfo.getValuationDate());
						loanODto.setProcessingDto(pfDto);
						loanODto.setStatus("processed");
					}
				}
				if(loan.getStatus()==3) {
					SanctionInfo sanctionInfo = sanctionInfoRepository.findSanctionInfoByLoanId(loan.getId());
					if(sanctionInfo == null)
						loanODto.setSanctionOutputDto(null);
					else {
						SanctionOutputDto sanctionODto = new SanctionOutputDto();
						sanctionODto.setLoanAmountSanctioned(sanctionInfo.getLoanAmountSanctioned());
						sanctionODto.setLoanClosureDate(sanctionInfo.getLoanClosureDate());
						sanctionODto.setMonthlyPayment(sanctionInfo.getMonthlyPayment());
						sanctionODto.setPaymentStartDate(sanctionInfo.getPaymentStartDate());
						sanctionODto.setTermOfLoan(sanctionInfo.getTermOfLoan());
						loanODto.setSanctionOutputDto(sanctionODto);
						loanODto.setStatus("sanctioned");
						}
					}
				
				loanODto.setRemark("");
				loanODto.setSanctionOutputDto(null);
				if(loan.getStatus()==1)
				   loanODto.setStatus("applied");
				
				else if(loan.getStatus()==4)
					loanODto.setStatus("rejected");
			}
			
			return loanOutputDtoList;
		}
	}

}
