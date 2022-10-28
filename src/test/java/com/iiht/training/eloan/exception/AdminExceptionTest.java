package com.iiht.training.eloan.exception;

import static com.iiht.training.eloan.testutils.TestUtils.exceptionTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.testReport;
import static com.iiht.training.eloan.testutils.TestUtils.businessTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.currentTest;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.eloan.controller.AdminController;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.model.exception.ExceptionResponse;
import com.iiht.training.eloan.service.AdminService;
import com.iiht.training.eloan.testutils.MasterData;

@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc
public class AdminExceptionTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	@Test
	public void admin_testRegisterClerkInvalidDataException() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		
		userDto.setFirstName("ab");
		
		when(this.adminService.registerClerk(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/register-clerk")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void admin_testRegisterManagerInvalidDataException() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		
		userDto.setFirstName("ab");
		when(this.adminService.registerManager(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/register-manager")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value()? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	
	/*@Test
	public void admin_testDisableManagerNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.adminService.disableManager(2L)).thenThrow(new ManagerNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-manager/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void admin_testEnableManagerNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Manager with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.adminService.enableManager(2L)).thenThrow(new ManagerNotFoundException("Manager with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-manager/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}

	
	@Test
	public void admin_testDisableClerkNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.adminService.disableClerk(2L)).thenThrow(new ClerkNotFoundException("Clerk with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-clerk/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void admin_testEnableClerkNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Clerk with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.adminService.enableClerk(2L)).thenThrow(new ClerkNotFoundException("Clerk with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-clerk/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void admin_testDisableCustomerNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Customer with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.adminService.disableCustomer(2L)).thenThrow(new CustomerNotFoundException("Customer with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-customer/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}
	
	@Test
	public void admin_testEnableCustomerNotFoundException() throws Exception {
		ExceptionResponse exResponse = new ExceptionResponse("Customer with Id - 2 not Found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		
		when(this.adminService.enableCustomer(2L)).thenThrow(new CustomerNotFoundException("Customer with Id - 2 not Found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-customer/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contains(exResponse.getMessage())? "true" : "false"),	
				exceptionTestFile);
		
	}*/
}
