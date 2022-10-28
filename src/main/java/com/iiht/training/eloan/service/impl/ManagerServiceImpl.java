package com.iiht.training.eloan.service.impl;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.entity.Loan;
import com.iiht.training.eloan.entity.ProcessingInfo;
import com.iiht.training.eloan.entity.SanctionInfo;
import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.exception.AlreadyFinalizedException;
import com.iiht.training.eloan.exception.InvalidDataException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.exception.ManagerNotFoundException;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.model.RejectDto;
import com.iiht.training.eloan.model.SanctionDto;
import com.iiht.training.eloan.model.SanctionOutputDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.repository.LoanRepository;
import com.iiht.training.eloan.repository.ProcessingInfoRepository;
import com.iiht.training.eloan.repository.SanctionInfoRepository;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private ProcessingInfoRepository processingInfoRepository;
	
	@Autowired
	private SanctionInfoRepository sanctionInfoRepository;
	
	@Override
	public List<LoanOutputDto> allProcessedLoans() {
		List<LoanOutputDto> loanODtoList = new ArrayList<LoanOutputDto>();
		List<Loan> loanList = loanRepository.findLoanByStatus(2);
		if(loanList.size() == 0)
			throw new InvalidDataException("No data for Loan Processed");
		else {
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
				loanODto.setProcessingDto(null);
				loanODto.setRemark("");
				loanODto.setSanctionOutputDto(null);
				loanODto.setStatus("processed");
				ProcessingDto processingDto = new ProcessingDto();
				ProcessingInfo pInfo = processingInfoRepository.findByLoanId(loan.getId());
				processingDto.setAcresOfLand(pInfo.getAcresOfLand());
				processingDto.setAddressOfProperty(pInfo.getAddressOfProperty());
				processingDto.setAppraisedBy(pInfo.getAppraisedBy());
				processingDto.setLandValue(pInfo.getLandValue());
				processingDto.setSuggestedAmountOfLoan(pInfo.getSuggestedAmountOfLoan());
				processingDto.setValuationDate(pInfo.getValuationDate());
				loanODto.setProcessingDto(processingDto);
				loanODtoList.add(loanODto);
			}
			return loanODtoList;
		}
			
		
	}

	@Override
	public RejectDto rejectLoan(Long managerId, Long loanAppId, RejectDto rejectDto) {
		Users user = usersRepository.findCustomerById("Manager", managerId);
		if(user == null) {
			throw new ManagerNotFoundException("Manager Not Found Exception for Id " +managerId);
			
		}
		
		Loan loan = loanRepository.findById(loanAppId).get();
		if(loan == null) {
			throw new LoanNotFoundException("Loan not found for Id " +loanAppId);
		}
		
		Loan sanctionedLoan = loanRepository.findLoanByStatusAndId(loanAppId, 3);
		if(sanctionedLoan != null)
			throw new AlreadyFinalizedException("Loan Already Sanction for Id " +loanAppId);
		else {
			loan.setStatus(4);
			loan.setRemark(rejectDto.getRemark());
			loanRepository.save(loan);
			return rejectDto;
		}
	}

	@Override
	public SanctionOutputDto sanctionLoan(Long managerId, Long loanAppId, SanctionDto sanctionDto) {
		Users user = usersRepository.findCustomerById("Manager", managerId);
		if(user == null) {
			throw new ManagerNotFoundException("Manager Not Found Exception for Id " +managerId);
		}
		Loan loan = loanRepository.findById(loanAppId).get();
		if(loan == null) {
			throw new LoanNotFoundException("Loan not found for Id " +loanAppId);
		}
		Loan sanctionedLoan = loanRepository.findLoanByStatusAndId(loanAppId, 3);
		if(sanctionedLoan != null)
			throw new AlreadyFinalizedException("Loan Already Sanction for Id " +loanAppId);
		else {
			SanctionInfo sInfo = new SanctionInfo();
			sInfo.setLoanAmountSanctioned(sanctionDto.getLoanAmountSanctioned());
			sInfo.setLoanAppId(loan.getId());
			sInfo.setTermOfLoan(sanctionDto.getTermOfLoan());
			LocalDate date = LocalDate.parse(sanctionDto.getPaymentStartDate(), DateTimeFormatter.ofPattern("dd/M/yyyy"));
			LocalDate closureDate = date.plusMonths(new Double(sanctionDto.getTermOfLoan()).longValue());
			sInfo.setLoanClosureDate(closureDate.format(DateTimeFormatter.ofPattern("dd/M/yyyy")).toString());
			sInfo.setManagerId(managerId);
			sInfo.setPaymentStartDate(sanctionDto.getPaymentStartDate());
			Double termPayment = sanctionDto.getLoanAmountSanctioned() * (Math.pow(1+6/100, (sInfo.getTermOfLoan()/12)));
			Double monthlyPayment = (termPayment/sInfo.getTermOfLoan());
			sInfo.setMonthlyPayment(monthlyPayment);
			sanctionInfoRepository.save(sInfo);
			loan.setStatus(3);
			loanRepository.save(loan);
			SanctionOutputDto sanctionODto = new SanctionOutputDto();
			sanctionODto.setLoanAmountSanctioned(sInfo.getLoanAmountSanctioned());
			sanctionODto.setLoanClosureDate(sInfo.getLoanClosureDate());
			sanctionODto.setMonthlyPayment(sInfo.getMonthlyPayment());
			sanctionODto.setPaymentStartDate(sInfo.getPaymentStartDate());
			sanctionODto.setTermOfLoan(sInfo.getTermOfLoan());
			return sanctionODto;
		}
		
	}

}
