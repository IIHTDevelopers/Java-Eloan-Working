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

import com.iiht.training.eloan.controller.ManagerController;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.RejectDto;
import com.iiht.training.eloan.model.SanctionDto;
import com.iiht.training.eloan.model.SanctionOutputDto;
import com.iiht.training.eloan.model.exception.ExceptionResponse;
import com.iiht.training.eloan.service.ManagerService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(ManagerController.class)
@AutoConfigureMockMvc
public class ManagerExceptionTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ManagerService managerService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void manager_testSanctionLoanInvalidDataException() throws Exception {
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		SanctionOutputDto sanctionOutputDto = MasterData.getSanctionOutputDto();
		
		sanctionDto.setLoanAmountSanctioned(0.0);
		when(this.managerService.sanctionLoan(1L, 1L, sanctionDto)).thenReturn(sanctionOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/1/1")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void manager_testRejectLoanManagerNotFoundException() throws Exception {
		RejectDto rejectDto = MasterData.getRejectDto();
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.rejectLoan(2L, 1L, rejectDto)).thenThrow(new ManagerNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/reject-loan/2/1")
				.content(MasterData.asJsonString(rejectDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void manager_testRejectLoanManagerDisabledException() throws Exception {
		RejectDto rejectDto = MasterData.getRejectDto();
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 is Disabled by Admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.rejectLoan(2L, 1L, rejectDto)).thenThrow(new ManagerDisabledException("Manager with Id - 2 is Disabled by Admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/reject-loan/2/1")
				.content(MasterData.asJsonString(rejectDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	@Test
	public void manager_testRejectLoanNotFoundException() throws Exception {
		RejectDto rejectDto = MasterData.getRejectDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.rejectLoan(1L, 2L, rejectDto)).thenThrow(new LoanNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/reject-loan/1/2")
				.content(MasterData.asJsonString(rejectDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	@Test
	public void manager_testRejectLoanAlreadyFinalizedException() throws Exception {
		RejectDto rejectDto = MasterData.getRejectDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 1 already finalized!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.rejectLoan(1L, 1L, rejectDto)).thenThrow(new AlreadyFinalizedException("Loan with Id - 1 already finalized!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/reject-loan/1/1")
				.content(MasterData.asJsonString(rejectDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void manager_testSanctionLoanManagerNotFoundException() throws Exception {
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.sanctionLoan(2L, 1L, sanctionDto)).thenThrow(new ManagerNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/2/1")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void manager_testSanctionLoanManagerDisabledException() throws Exception {
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 is disabled by admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.sanctionLoan(2L, 1L, sanctionDto)).thenThrow(new ManagerDisabledException("Manager with Id - 2 is disabled by admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/2/1")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	@Test
	public void manager_testSanctionLoanNotFoundException() throws Exception {
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.sanctionLoan(1L, 2L, sanctionDto)).thenThrow(new LoanNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/1/2")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	@Test
	public void manager_testSanctionLoanAlreadyFinalizedException() throws Exception {
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		ExceptionResponse exResponse = new ExceptionResponse("Loan with Id - 1 already finalized!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.sanctionLoan(1L, 1L, sanctionDto)).thenThrow(new AlreadyFinalizedException("Loan with Id - 1 already finalized!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/1/1")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	/*@Test
	public void manager_testAllFinalizedLoanManagerNotFoundException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.allFinalizedLoans(2L)).thenThrow(new ManagerNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/manager/all-finalized/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	/*@Test
	public void manager_testAllFinalizedLoanManagerDisabledException() throws Exception {
		
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 is Disabled by Admin!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.managerService.allFinalizedLoans(2L)).thenThrow(new ManagerDisabledException("Manager with Id - 2 is Disabled by Admin!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/manager/all-finalized/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
	
	
}
