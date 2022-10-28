package com.iiht.training.eloan.functional;

import static com.iiht.training.eloan.testutils.TestUtils.businessTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.currentTest;
import static com.iiht.training.eloan.testutils.TestUtils.yakshaAssert;
import static com.iiht.training.eloan.testutils.TestUtils.testReport;
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

import com.iiht.training.eloan.controller.AdminController;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.service.AdminService;
import com.iiht.training.eloan.testutils.MasterData;


@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc
public class AdminControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@AfterAll
	public static void afterAll() {
		testReport();
	}
	
	
	@Test
	public void admin_testRegisterClerk() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		
		when(this.adminService.registerClerk(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/register-clerk")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void admin_testRegisterClerkIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		when(this.adminService.registerClerk(userDto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedUserDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/register-clerk")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}
	
	@Test
	public void admin_testGetAllClerks() throws Exception {
		List<UserDto> userDtos = MasterData.getUserDtoList();
		
		when(this.adminService.getAllClerks()).thenReturn(userDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/all-clerks")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDtos))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void admin_testGetAllClerksIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<UserDto> userDtos = MasterData.getUserDtoList();
		when(this.adminService.getAllClerks()).then(new Answer<List<UserDto>>() {

			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/all-clerks")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}
	
	@Test
	public void admin_testRegisterManager() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		
		when(this.adminService.registerManager(userDto)).thenReturn(savedUserDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/register-manager")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedUserDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void admin_testRegisterManagerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		UserDto savedUserDto = MasterData.getUserDto();
		savedUserDto.setId(1L);
		when(this.adminService.registerManager(userDto)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedUserDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/register-manager")
				.content(MasterData.asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}
	
	@Test
	public void admin_testGetAllManagers() throws Exception {
		List<UserDto> userDtos = MasterData.getUserDtoList();
		
		when(this.adminService.getAllManagers()).thenReturn(userDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/all-managers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDtos))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void admin_testGetAllManagersIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<UserDto> userDtos = MasterData.getUserDtoList();
		when(this.adminService.getAllManagers()).then(new Answer<List<UserDto>>() {

			@Override
			public List<UserDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/all-managers")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}
	
	/*@Test
	public void admin_testDisableManager() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		
		when(this.adminService.disableManager(1L)).thenReturn(userDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-manager/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDto))? "true" : "false"),	
				businessTestFile);
		
	}
	
	@Test
	public void admin_testDisableManagerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		when(this.adminService.disableManager(1L)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-manager/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	
	/*@Test
	public void admin_testEnableManager() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		
		when(this.adminService.enableManager(1L)).thenReturn(userDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-manager/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDto))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void admin_testEnableManagerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		when(this.adminService.enableManager(1L)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-manager/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	/**********Clerk*******************/
	/*@Test
	public void admin_testDisableClerk() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		
		when(this.adminService.disableClerk(1L)).thenReturn(userDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-clerk/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDto))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void admin_testDisableClerkIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		when(this.adminService.disableClerk(1L)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-clerk/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	
	/*@Test
	public void admin_testEnableClerk() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		
		when(this.adminService.enableClerk(1L)).thenReturn(userDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-clerk/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDto))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void admin_testEnableClerkIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		when(this.adminService.enableClerk(1L)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-clerk/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	
	/*************Customer******************/
	
	/*@Test
	public void admin_testDisableCustomer() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		
		when(this.adminService.disableCustomer(1L)).thenReturn(userDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-customer/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDto))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void admin_testDisableCustomerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		when(this.adminService.disableCustomer(1L)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/disable-customer/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
	
	/*@Test
	public void admin_testEnableCustomer() throws Exception {
		UserDto userDto = MasterData.getUserDto();
		
		when(this.adminService.enableCustomer(1L)).thenReturn(userDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-customer/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDto))? "true" : "false"),	
				businessTestFile);
		
	}*/
	
	/*@Test
	public void admin_testEnableCustomerIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		UserDto userDto = MasterData.getUserDto();
		when(this.adminService.enableCustomer(1L)).then(new Answer<UserDto>() {

			@Override
			public UserDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return userDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/enable-customer/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
	}*/
}
