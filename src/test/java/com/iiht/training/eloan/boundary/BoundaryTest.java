package com.iiht.training.eloan.boundary;

import static com.iiht.training.eloan.testutils.TestUtils.currentTest;
import static com.iiht.training.eloan.testutils.TestUtils.testReport;
import static com.iiht.training.eloan.testutils.TestUtils.boundaryTestFile;
import static com.iiht.training.eloan.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.ProcessingDto;
import com.iiht.training.eloan.model.SanctionDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.testutils.MasterData;

@ExtendWith(SpringExtension.class)
// @WebMvcTest
public class BoundaryTest {
	private static Validator validator;

    //----------------------------------------------------------------------------------------------
    @BeforeAll
    public static void setUp() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @AfterAll
	public static void afterAll() {
		testReport();
	}
    
    @Test
	public void admin_testUserFirstNameNotNull() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setFirstName("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserFirstNameMinThree() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setFirstName("Ab");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserFirstNameMaxHundred() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		String name = "";
		for(int i=0;i<120;i++) {
			name.concat("A");
		}
		userDto.setFirstName(name);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserLastNameNotNull() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setLastName("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserLastNameMinThree() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setLastName("Ab");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserLastNameMaxHundred() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		String name = "";
		for(int i=0;i<120;i++) {
			name.concat("A");
		}
		userDto.setLastName(name);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    
    @Test
	public void admin_testUserEmailNotNull() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setEmail("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserEmailMinThree() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setEmail("Ab");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserEmailMaxHundred() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		String name = "";
		for(int i=0;i<120;i++) {
			name.concat("A");
		}
		userDto.setEmail(name);
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserEmailValidFormat() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setEmail("abc");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserMobileNotNull() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setMobile("");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserMobileMinTen() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		userDto.setMobile("12345");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testUserMobileMaxTen() throws Exception 
	{
		UserDto userDto =  MasterData.getUserDto();
		
		userDto.setMobile("123456789001");
		Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanNameNotNull() throws Exception 
	{
		LoanDto loanDto = MasterData.getLoanDto();
		loanDto.setLoanName(null);
		Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanNameMinThree() throws Exception 
	{
    	LoanDto loanDto = MasterData.getLoanDto();
		loanDto.setLoanName("ab");
		Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanNameMaxHundred() throws Exception 
	{
    	LoanDto loanDto = MasterData.getLoanDto();
		String name = "";
		for(int i=0;i<120;i++) {
			name.concat("A");
		}
		loanDto.setLoanName(name);
		Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanAmountNotNull() throws Exception 
	{
		LoanDto loanDto = MasterData.getLoanDto();
		loanDto.setLoanAmount(null);
		Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanAmountNotZero() throws Exception 
	{
    	LoanDto loanDto = MasterData.getLoanDto();
		loanDto.setLoanAmount(0.0);
		Set<ConstraintViolation<LoanDto>> violations = validator.validate(loanDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testAcresOfLandNotNull() throws Exception 
	{
		ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setAcresOfLand(null);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testAcresOfLandNotZero() throws Exception 
	{
    	ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setAcresOfLand(0.0);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLandValueNotNull() throws Exception 
	{
		ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setLandValue(null);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLandValueNotZero() throws Exception 
	{
    	ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setLandValue(0.0);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testSuggestedAmountOfLoanNotNull() throws Exception 
	{
		ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setSuggestedAmountOfLoan(null);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testSuggestedAmountOfLoanNotZero() throws Exception 
	{
    	ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setSuggestedAmountOfLoan(0.0);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testAddressOfPropertyNotNull() throws Exception 
	{
    	ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setAddressOfProperty(null);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testAddressOfPropertyMinThree() throws Exception 
	{
    	ProcessingDto processingDto =  MasterData.getProcessingDto();
		processingDto.setAddressOfProperty("ab");
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testAddressOfPropertyMaxOneFifty() throws Exception 
	{
    	ProcessingDto processingDto =  MasterData.getProcessingDto();
		String name = "";
		for(int i=0;i<160;i++) {
			name.concat("A");
		}
		processingDto.setAddressOfProperty(name);
		Set<ConstraintViolation<ProcessingDto>> violations = validator.validate(processingDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanAmountSanctionedNotNull() throws Exception 
	{
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		sanctionDto.setLoanAmountSanctioned(null);
		Set<ConstraintViolation<SanctionDto>> violations = validator.validate(sanctionDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testLoanAmountSanctionedNotZero() throws Exception 
	{
    	SanctionDto sanctionDto = MasterData.getSanctionDto();
		sanctionDto.setLoanAmountSanctioned(0.0);
		Set<ConstraintViolation<SanctionDto>> violations = validator.validate(sanctionDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testTermOfLoanNotNull() throws Exception 
	{
		SanctionDto sanctionDto = MasterData.getSanctionDto();
		sanctionDto.setTermOfLoan(null);
		Set<ConstraintViolation<SanctionDto>> violations = validator.validate(sanctionDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
    
    @Test
	public void admin_testTermOfLoanNNotZero() throws Exception 
	{
    	SanctionDto sanctionDto = MasterData.getSanctionDto();
		sanctionDto.setTermOfLoan(0.0);
		Set<ConstraintViolation<SanctionDto>> violations = validator.validate(sanctionDto);
		yakshaAssert(currentTest(), !violations.isEmpty()? true : false, boundaryTestFile);
	}
}
