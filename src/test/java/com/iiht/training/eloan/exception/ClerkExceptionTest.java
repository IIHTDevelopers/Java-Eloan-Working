package com.iiht.training.eloan.exception;

import static com.iiht.training.eloan.testutils.TestUtils.businessTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.currentTest;
import static com.iiht.training.eloan.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.testReport;
import static com.iiht.training.eloan.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.List;

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

import com.iiht.training.eloan.controller.ClerkController;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.model.exception.ExceptionResponse;
import com.iiht.training.eloan.service.ClerkService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(ClerkController.class)
@AutoConfigureMockMvc
public class ClerkExceptionTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ClerkService clerkService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void clerk_testProcessLoanInvalidDataException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		
		processingDto.setAcresOfLand(0.0);
		when(this.clerkService.processLoan(1L, 1L, processingDto)).thenReturn(processingDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void clerk_testUpdateProcessLoanInvalidDataException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		processingDto.setAcresOfLand(0.0);
		when(this.clerkService.updateProcessInfo(1L, 1L, processingDto)).thenReturn(processingDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	@Test
	public void clerk_testProcessLoanClerkNotFoundException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.processLoan(2L, 1L, processingDto)).thenThrow(new ClerkNotFoundException("Clerk with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/2/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void clerk_testProcessLoanClerkDisabledException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 is disabled by admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.processLoan(2L, 1L, processingDto)).thenThrow(new ClerkDisabledException("Clerk with Id - 2 is disabled by admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/2/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	@Test
	public void clerk_testProcessLoanNotFoundException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.processLoan(1L, 2L, processingDto)).thenThrow(new LoanNotFoundException("Loan with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/1/2")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	@Test
	public void clerk_testAlreadyProcessedException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 1 alredy processed!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.processLoan(1L, 1L, processingDto)).thenThrow(new AlreadyProcessedException("Loan with Id - 1 alredy processed!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/clerk/process/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*****************Update Process***************************/
	/*@Test
	public void clerk_testUpdateProcessLoanClerkNotFoundException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.updateProcessInfo(2L, 1L, processingDto)).thenThrow(new ClerkNotFoundException("Clerk with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/2/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void clerk_testUpdateProcessLoanClerkDisabledException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 is disabled by admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.updateProcessInfo(2L, 1L, processingDto)).thenThrow(new ClerkDisabledException("Clerk with Id - 2 is disabled by admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/2/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void clerk_testUpdateProcessLoanNotFoundException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.updateProcessInfo(1L, 2L, processingDto)).thenThrow(new LoanNotFoundException("Loan with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/1/2")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	/*@Test
	public void clerk_testUpdateAlreadyFinalizedException() throws Exception {
		ProcessingDto processingDto = MasterData.getProcessingDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 1 already finalized!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.updateProcessInfo(1L, 1L, processingDto)).thenThrow(new AlreadyFinalizedException("Loan with Id - 1 already finalized!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/clerk/process-update/1/1")
				.content(MasterData.asJsonString(processingDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void clerk_testGetAllProcessedLoanClerkNotFoundException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.getProcessedLoanStatus(2L)).thenThrow(new ClerkNotFoundException("Clerk with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clerk/all-processed/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void clerk_testGetAllProcessedLoanClerkDisabledException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 is disabled by admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.clerkService.getProcessedLoanStatus(2L)).thenThrow(new ClerkDisabledException("Clerk with Id - 2 is disabled by admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/clerk/all-processed/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	
	
}
