package com.iiht.training.eloan.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.training.eloan.exception.CustomerNotFoundException;
import com.iiht.training.eloan.exception.LoanNotFoundException;
import com.iiht.training.eloan.model.LoanDto;
import com.iiht.training.eloan.model.LoanOutputDto;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.model.exception.ExceptionResponse;
import com.iiht.training.eloan.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto){
		return new ResponseEntity<UserDto>(customerService.register(userDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/apply-loan/{customerId}")
	public ResponseEntity<LoanOutputDto> applyLoan(@PathVariable Long customerId,
												@Valid @RequestBody LoanDto loanDto){
		LoanOutputDto loanODto = customerService.applyLoan(customerId, loanDto);
		if(loanODto == null) {
			throw new CustomerNotFoundException("Customer with Id "+customerId+" not found");
		}else
		  return new ResponseEntity<LoanOutputDto>(loanODto,HttpStatus.OK);
	}
	
	@GetMapping("/loan-status/{loanAppId}")
	public ResponseEntity<LoanOutputDto> getStatus(@PathVariable Long loanAppId){
		LoanOutputDto loanODto = customerService.getStatus(loanAppId);
		if(loanODto == null) {
			throw new LoanNotFoundException("Customer Loan with Id "+loanAppId+" not found");
		}else
		return new ResponseEntity<LoanOutputDto>(loanODto, HttpStatus.OK);
	}
	
	@GetMapping("/loan-status-all/{customerId}")
	public ResponseEntity<List<LoanOutputDto>> getStatusAll(@PathVariable Long customerId){
		List<LoanOutputDto> loanODto = customerService.getStatusAll(customerId);
		if(loanODto == null || loanODto.size() == 0) {
			throw new LoanNotFoundException("Customer Loan with customer Id "+customerId+" not found");
		}else
		return new ResponseEntity<List<LoanOutputDto>>(loanODto, HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(CustomerNotFoundException ex){
		ExceptionResponse exception = 
				new ExceptionResponse(ex.getMessage(),
									  System.currentTimeMillis(),
									  HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response =
				new ResponseEntity<ExceptionResponse>(exception, HttpStatus.NOT_FOUND);
		return response;
	}
}
