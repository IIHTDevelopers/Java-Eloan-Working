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

import com.iiht.training.eloan.controller.ClerkController;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.service.ClerkService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(ClerkController.class)
@AutoConfigureMockMvc
public class ClerkControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClerkService clerkService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void clerk_testAllAppliedLoans() throws Exception {
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForClerk();
		
		when(this.clerkService.allAppliedLoans()).thenReturn(loanOutputDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clerk/all-applied")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDtos))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void clerk_testAllAppliedLoansIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForClerk();
		when(this.clerkService.allAppliedLoans()).then(new Answer<List<LoanOutputDto>>() {

			@Override
			public List<LoanOutputDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clerk/all-applied")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	@Test
	public void clerk_testProcessLoan() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		
		when(this.clerkService.processLoan(1L, 1L, processingDto)).thenReturn(processingDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(processingDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void clerk_testProcessLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		ProcessingDto processingDto = MasterData.getProcessingDto();
		when(this.clerkService.processLoan(1L, 1L, processingDto)).then(new Answer<ProcessingDto>() {

			@Override
			public ProcessingDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return processingDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	/*@Test
	public void clerk_testUpdateProcessLoan() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		
		when(this.clerkService.updateProcessInfo(1L, 1L, processingDto)).thenReturn(processingDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(processingDto))? "true" : "false"),	
				businessTestFile);
		
	}
	*/
	/*@Test
	public void clerk_testUpdateProcessLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		ProcessingDto processingDto = MasterData.getProcessingDto();
		when(this.clerkService.updateProcessInfo(1L, 1L, processingDto)).then(new Answer<ProcessingDto>() {

			@Override
			public ProcessingDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return processingDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	
	/*@Test
	public void clerk_testGetAllProcessed() throws Exception {
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForClerk();
		
		when(this.clerkService.getProcessedLoanStatus(1L)).thenReturn(loanOutputDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clerk/all-processed/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDtos))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void clerk_testGetAllProcessedIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForClerk();
		when(this.clerkService.getProcessedLoanStatus(1L)).then(new Answer<List<LoanOutputDto>>() {

			@Override
			public List<LoanOutputDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clerk/all-processed/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
}
