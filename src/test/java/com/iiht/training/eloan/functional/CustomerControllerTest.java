package com.iiht.training.eloan.functional;

import static com.iiht.training.eloan.testutils.TestUtils.businessTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.currentTest;
import static com.iiht.training.eloan.testutils.TestUtils.testReport;
import static com.iiht.training.eloan.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.eloan.controller.CustomerController;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.service.CustomerService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CustomerService customerService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void customer_testRegisterCustomer() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		
		when(this.customerService.register(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/register")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void customer_testRegisterCustomerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		when(this.customerService.register(userDto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedUserDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/register")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}
	
	@Test
	public void customer_testApplyLoan() throws Exception {
		LoanDto loanDto = MasterData.getLoanDto();
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.applyLoan(1L, loanDto)).thenReturn(loanOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/apply-loan/1")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void customer_testApplyLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LoanDto loanDto = MasterData.getLoanDto();
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.applyLoan(1L, loanDto)).then(new Answer<LoanOutputDto>() {

			@Override
			public LoanOutputDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/apply-loan/1")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}
	
	
	@Test
	public void customer_testGetStatus() throws Exception {
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.getStatus(1L)).thenReturn(loanOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void customer_testGetStatusIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		when(this.customerService.getStatus(1L)).then(new Answer<LoanOutputDto>() {

			@Override
			public LoanOutputDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	@Test
	public void customer_testGetStatusAll() throws Exception {
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForCustomer();
		
		when(this.customerService.getStatusAll(1L)).thenReturn(loanOutputDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status-all/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDtos))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void customer_testGetStatusAllIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForCustomer();
		when(this.customerService.getStatusAll(1L)).then(new Answer<List<LoanOutputDto>>() {

			@Override
			public List<LoanOutputDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/loan-status-all/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	/*@Test
	public void customer_testUpdateLoan() throws Exception {
		LoanDto loanDto = MasterData.getLoanDto();
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.updateLoanInfo(1L, loanDto)).thenReturn(loanOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/loan-update/1")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDto))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void customer_testUpdateLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		LoanDto loanDto = MasterData.getLoanDto();
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.updateLoanInfo(1L, loanDto)).then(new Answer<LoanOutputDto>() {

			@Override
			public LoanOutputDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/customer/loan-update/1")
				.content(MasterData.asJsonString(loanDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}*/
	
	/*@Test
	public void customer_testDeleteLoan() throws Exception {
		
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.deleteLoan(1L)).thenReturn(loanOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/loan-delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDto))? "true" : "false"),	
				businessTestFile);
		
	}
	*/
	/*@Test
	public void customer_testDeleteLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		
		LoanOutputDto loanOutputDto = MasterData.getLoanOutputDto();
		
		when(this.customerService.deleteLoan(1L)).then(new Answer<LoanOutputDto>() {

			@Override
			public LoanOutputDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer/loan-delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}*/
	
}
