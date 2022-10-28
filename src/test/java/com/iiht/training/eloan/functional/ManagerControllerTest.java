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

import com.iiht.training.eloan.controller.ManagerController;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.RejectDto;
import com.iiht.training.eloan.model.SanctionDto;
import com.iiht.training.eloan.model.SanctionOutputDto;
import com.iiht.training.eloan.service.ManagerService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(ManagerController.class)
@AutoConfigureMockMvc
public class ManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ManagerService managerService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void manager_testAllProcessedLoans() throws Exception {
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForManager();
		
		when(this.managerService.allProcessedLoans()).thenReturn(loanOutputDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/manager/all-processed")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDtos))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void manager_testAllProcessedLoansIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForManager();
		when(this.managerService.allProcessedLoans()).then(new Answer<List<LoanOutputDto>>() {

			@Override
			public List<LoanOutputDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/manager/all-processed")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	@Test
	public void manager_testRejectLoan() throws Exception {
		RejectDto rejectDto = MasterData.getRejectDto();
		
		when(this.managerService.rejectLoan(1L, 1L, rejectDto)).thenReturn(rejectDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/reject-loan/1/1")
				.content(MasterData.asJsonString(rejectDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(rejectDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void manager_testRejectLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		RejectDto rejectDto = MasterData.getRejectDto();
		when(this.managerService.rejectLoan(1L, 1L, rejectDto)).then(new Answer<RejectDto>() {

			@Override
			public RejectDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return rejectDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/reject-loan/1/1")
				.content(MasterData.asJsonString(rejectDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}

	@Test
	public void manager_testSanctionLoan() throws Exception {
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		SanctionOutputDto sanctionOutputDto = MasterData.getSanctionOutputDto();
		
		when(this.managerService.sanctionLoan(1L, 1L, sanctionDto)).thenReturn(sanctionOutputDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/1/1")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(sanctionOutputDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void manager_testSanctionLoanIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		SanctionOutputDto sanctionOutputDto = MasterData.getSanctionOutputDto();
		when(this.managerService.sanctionLoan(1L, 1L, sanctionDto)).then(new Answer<SanctionOutputDto>() {

			@Override
			public SanctionOutputDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return sanctionOutputDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/manager/sanction-loan/1/1")
				.content(MasterData.asJsonString(sanctionDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	/*@Test
	public void manager_testAllFinalizedLoans() throws Exception {
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForManager();
		
		when(this.managerService.allFinalizedLoans(1L)).thenReturn(loanOutputDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/manager/all-finalized/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(loanOutputDtos))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void manager_testAllFinalizedLoansIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<LoanOutputDto> loanOutputDtos = MasterData.getLoanOutputDtoListForManager();
		when(this.managerService.allFinalizedLoans(1L)).then(new Answer<List<LoanOutputDto>>() {

			@Override
			public List<LoanOutputDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return loanOutputDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/manager/all-finalized/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	
}
