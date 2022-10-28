package com.iiht.training.eloan.testutils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.model.RejectDto;
import com.iiht.training.eloan.model.SanctionDto;
import com.iiht.training.eloan.model.SanctionOutputDto;
import com.iiht.training.eloan.model.UserDto;

public class MasterData {

	public static UserDto getUserDto() {
		UserDto userDto = new UserDto();
		userDto.setFirstName("First");
		userDto.setLastName("Last");
		userDto.setEmail("first@mail.com");
		userDto.setMobile("1234567890");
		return userDto;
	}
	
	
	public static List<UserDto> getUserDtoList() {
		List<UserDto> userDtos = new ArrayList<UserDto>();
		UserDto userDto = new UserDto();
		userDto.setId(1L);
		userDto.setFirstName("First");
		userDto.setLastName("Last");
		userDto.setEmail("first@mail.com");
		userDto.setMobile("1234567890");
		userDtos.add(userDto);
		userDto = new UserDto();
		userDto.setId(2L);
		userDto.setFirstName("Second");
		userDto.setLastName("Last");
		userDto.setEmail("second@mail.com");
		userDto.setMobile("1234567890");
		userDtos.add(userDto);
		return userDtos;
	}
	
	public static LoanDto getLoanDto() {
		LoanDto loanDto = new LoanDto();
		loanDto.setLoanName("Mortgage");
		loanDto.setLoanApplicationDate("12/04/2021");
		loanDto.setBillingIndicator("Salaried");
		loanDto.setBusinessStructure("Individual");
		loanDto.setLoanAmount(500000.00);
		loanDto.setTaxIndicator("Payer");
		return loanDto;
	}
	
	public static ProcessingDto getProcessingDto() {
		ProcessingDto processingDto = new ProcessingDto();
		processingDto.setAcresOfLand(2.0);
		processingDto.setAppraisedBy("Appraiser1");
		processingDto.setAddressOfProperty("Some Location");
		processingDto.setLandValue(100000.00);
		processingDto.setSuggestedAmountOfLoan(600000.00);
		processingDto.setValuationDate("21/04/2021");
		return processingDto;
	}
	
	public static List<LoanOutputDto> getLoanOutputDtoListForClerk() {
		List<LoanOutputDto> loanDtos = new ArrayList<LoanOutputDto>();
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(1L);
		loanOutputDto.setLoanAppId(1L);
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(null);
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(null);
		loanOutputDto.setStatus("applied");
		loanDtos.add(loanOutputDto);
		
		loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(2L);
		loanOutputDto.setLoanAppId(2L);
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(null);
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(null);
		loanOutputDto.setStatus("applied");
		loanDtos.add(loanOutputDto);
		
		return loanDtos;
	}
	
	public static RejectDto getRejectDto() {
		RejectDto rejectDto = new RejectDto();
		rejectDto.setRemark("Property Disputed!");
		
		return rejectDto;
	}
	
	public static SanctionDto getSanctionDto() {
		SanctionDto sanctionDto = new SanctionDto();
		sanctionDto.setLoanAmountSanctioned(400000.00);
		sanctionDto.setPaymentStartDate("20/6/2021");
		sanctionDto.setTermOfLoan(120.0);
		return sanctionDto;
	}
	
	public static SanctionOutputDto getSanctionOutputDto() {
		SanctionOutputDto sanctionOutputDto = new SanctionOutputDto();
		sanctionOutputDto.setLoanAmountSanctioned(400000.00);
		sanctionOutputDto.setPaymentStartDate("20/6/2021");
		sanctionOutputDto.setTermOfLoan(120.0);
		sanctionOutputDto.setLoanClosureDate("20/6/2031");
		sanctionOutputDto.setMonthlyPayment(4000.00);
		return sanctionOutputDto;
	}
	
	public static List<LoanOutputDto> getLoanOutputDtoListForManager() {
		List<LoanOutputDto> loanDtos = new ArrayList<LoanOutputDto>();
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(1L);
		loanOutputDto.setLoanAppId(1L);
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(getProcessingDto());
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(null);
		loanOutputDto.setStatus("processed");
		loanDtos.add(loanOutputDto);
		
		loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(2L);
		loanOutputDto.setLoanAppId(2L);
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(getProcessingDto());
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(null);
		loanOutputDto.setStatus("processed");
		loanDtos.add(loanOutputDto);
		
		return loanDtos;
	}
	
	public static List<LoanOutputDto> getLoanOutputDtoListForCustomer() {
		List<LoanOutputDto> loanDtos = new ArrayList<LoanOutputDto>();
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(1L);
		loanOutputDto.setLoanAppId(1L);
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(getProcessingDto());
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(null);
		loanOutputDto.setStatus("processed");
		loanDtos.add(loanOutputDto);
		
		loanOutputDto = new LoanOutputDto();
		loanOutputDto.setCustomerId(1L);
		loanOutputDto.setLoanAppId(2L);
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(getProcessingDto());
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(getSanctionOutputDto());
		loanOutputDto.setStatus("sanctioned");
		loanDtos.add(loanOutputDto);
		
		return loanDtos;
	}
	
	public static LoanOutputDto getLoanOutputDto() {
		LoanOutputDto loanOutputDto = new LoanOutputDto();
		loanOutputDto.setUserDto(getUserDto());
		loanOutputDto.setCustomerId(1L);
		loanOutputDto.setLoanAppId(1L);
		loanOutputDto.setLoanDto(getLoanDto());
		loanOutputDto.setProcessingDto(null);
		loanOutputDto.setRemark("");
		loanOutputDto.setSanctionOutputDto(null);
		loanOutputDto.setStatus("applied");
		
		return loanOutputDto;
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
}
