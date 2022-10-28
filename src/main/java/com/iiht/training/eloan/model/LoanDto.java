package com.iiht.training.eloan.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoanDto {

	@Size(min=3, max=100, message="Loan Name characters should be contain atleast 3 characters. max characters 100.")
	@NotEmpty(message = "Loan Name cannot be empty")
	private String loanName;
	
	
	@DecimalMin(value="1.0", message = "Please Enter a valid Loan Amount")
	@NotNull(message = "Loan Amount cannot be empty")
	private Double loanAmount;
	
	private String loanApplicationDate;
	private String businessStructure;
	private String billingIndicator;
	private String taxIndicator;
	public String getLoanName() {
		return loanName;
	}
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanApplicationDate() {
		return loanApplicationDate;
	}
	public void setLoanApplicationDate(String loanApplicationDate) {
		this.loanApplicationDate = loanApplicationDate;
	}
	public String getBusinessStructure() {
		return businessStructure;
	}
	public void setBusinessStructure(String businessStructure) {
		this.businessStructure = businessStructure;
	}
	public String getBillingIndicator() {
		return billingIndicator;
	}
	public void setBillingIndicator(String billingIndicator) {
		this.billingIndicator = billingIndicator;
	}
	public String getTaxIndicator() {
		return taxIndicator;
	}
	public void setTaxIndicator(String taxIndicator) {
		this.taxIndicator = taxIndicator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingIndicator == null) ? 0 : billingIndicator.hashCode());
		result = prime * result + ((businessStructure == null) ? 0 : businessStructure.hashCode());
		result = prime * result + ((loanAmount == null) ? 0 : loanAmount.hashCode());
		result = prime * result + ((loanApplicationDate == null) ? 0 : loanApplicationDate.hashCode());
		result = prime * result + ((loanName == null) ? 0 : loanName.hashCode());
		result = prime * result + ((taxIndicator == null) ? 0 : taxIndicator.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanDto other = (LoanDto) obj;
		if (billingIndicator == null) {
			if (other.billingIndicator != null)
				return false;
		} else if (!billingIndicator.equals(other.billingIndicator))
			return false;
		if (businessStructure == null) {
			if (other.businessStructure != null)
				return false;
		} else if (!businessStructure.equals(other.businessStructure))
			return false;
		if (loanAmount == null) {
			if (other.loanAmount != null)
				return false;
		} else if (!loanAmount.equals(other.loanAmount))
			return false;
		if (loanApplicationDate == null) {
			if (other.loanApplicationDate != null)
				return false;
		} else if (!loanApplicationDate.equals(other.loanApplicationDate))
			return false;
		if (loanName == null) {
			if (other.loanName != null)
				return false;
		} else if (!loanName.equals(other.loanName))
			return false;
		if (taxIndicator == null) {
			if (other.taxIndicator != null)
				return false;
		} else if (!taxIndicator.equals(other.taxIndicator))
			return false;
		return true;
	}
	
	
}
