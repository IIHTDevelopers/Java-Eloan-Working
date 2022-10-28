package com.iiht.training.eloan.exception;

import static com.iiht.training.eloan.testutils.TestUtils.businessTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.currentTest;
import static com.iiht.training.eloan.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.testReport;
import static com.iiht.training.eloan.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.eloan.controller.CustomerController;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.model.exception.ExceptionResponse;
import com.iiht.training.eloan.service.CustomerService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
public class CustomerExceptionTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void customer_testRegisterCustomerInvalidDataException() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		
		userDto.setFirstName("ab");
		
		when(this.customerService.register(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/register")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void customer_testApplyLoanInvalidDataException() throws Exception {
		LoanDto loanDto = MasterData.getLoanDto();
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		loanDto.setLoanName("ab");
		when(this.customerService.applyLoan(1L, loanDto)).thenReturn(loanOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/apply-loan/1")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void customer_testUpdateLoanInvalidDataException() throws Exception {
		LoanDto loanDto = MasterData.getLoanDto();
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		loanDto.setLoanName("ab");
		when(this.customerService.updateLoanInfo(1L, loanDto)).thenReturn(loanOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/loan-update/1")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	@Test
	public void customer_testApplyLoanCustomerNotFoundException() throws Exception {
		LoanDto loanDto = MasterData.getLoanDto();
		ExceptionResponse exResponse = new ExceptionResponse("Customer with Id - 2 not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.applyLoan(2L, loanDto)).thenThrow(new CustomerNotFoundException("Customer with Id - 2 not found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/apply-loan/2")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void customer_testApplyLoanCustomerDisabledException() throws Exception {
		LoanDto loanDto = MasterData.getLoanDto();
		ExceptionResponse exResponse = new ExceptionResponse("Customer with Id - 2 is disabled by admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.applyLoan(2L, loanDto)).thenThrow(new CustomerDisabledException("Customer with Id - 2 is disabled by admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/apply-loan/2")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	*/
	@Test
	public void customer_testGetStatusLoanNotFoundException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.getStatus(2L)).thenThrow(new LoanNotFoundException("Loan with Id - 2 not found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void customer_testGetStatusAllCustomerNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Customer with Id - 2 not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.getStatusAll(2L)).thenThrow(new CustomerNotFoundException("Customer with Id - 2 not found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status-all/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void customer_testGetStatusAllCustomerDisabledException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Customer with Id - 2 is disabled by admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.getStatusAll(2L)).thenThrow(new CustomerDisabledException("Customer with Id - 2 is disabled by admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status-all/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void customer_testUpdateLoanNotFoundException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		LoanDto loanDto = MasterData.getLoanDto();
		when(this.customerService.updateLoanInfo(2L, loanDto)).thenThrow(new LoanNotFoundException("Loan with Id - 2 not found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/loan-update/2")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void customer_testDeleteLoanNotFoundException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.deleteLoan(2L)).thenThrow(new LoanNotFoundException("Loan with Id - 2 not found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/loan-delete/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void customer_testUpdateLoanAlreadyProcessedException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 already processed!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		LoanDto loanDto = MasterData.getLoanDto();
		when(this.customerService.updateLoanInfo(2L, loanDto)).thenThrow(new AlreadyProcessedException("Loan with Id - 2 already processed!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/loan-update/2")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void customer_testDeleteLoanAlreadyProcessedException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 already processed!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.customerService.deleteLoan(2L)).thenThrow(new AlreadyProcessedException("Loan with Id - 2 already processed!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/loan-delete/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
}
