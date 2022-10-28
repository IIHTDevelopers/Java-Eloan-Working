package com.iiht.training.eloan.model;

public class SanctionOutputDto {
	private Double loanAmountSanctioned;
	private Double termOfLoan;
	private String paymentStartDate;
	private String loanClosureDate;
	private Double monthlyPayment;
	public Double getLoanAmountSanctioned() {
		return loanAmountSanctioned;
	}
	public void setLoanAmountSanctioned(Double loanAmountSanctioned) {
		this.loanAmountSanctioned = loanAmountSanctioned;
	}
	public Double getTermOfLoan() {
		return termOfLoan;
	}
	public void setTermOfLoan(Double termOfLoan) {
		this.termOfLoan = termOfLoan;
	}
	public String getPaymentStartDate() {
		return paymentStartDate;
	}
	public void setPaymentStartDate(String paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}
	public String getLoanClosureDate() {
		return loanClosureDate;
	}
	public void setLoanClosureDate(String loanClosureDate) {
		this.loanClosureDate = loanClosureDate;
	}
	public Double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(Double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loanAmountSanctioned == null) ? 0 : loanAmountSanctioned.hashCode());
		result = prime * result + ((loanClosureDate == null) ? 0 : loanClosureDate.hashCode());
		result = prime * result + ((monthlyPayment == null) ? 0 : monthlyPayment.hashCode());
		result = prime * result + ((paymentStartDate == null) ? 0 : paymentStartDate.hashCode());
		result = prime * result + ((termOfLoan == null) ? 0 : termOfLoan.hashCode());
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
		SanctionOutputDto other = (SanctionOutputDto) obj;
		if (loanAmountSanctioned == null) {
			if (other.loanAmountSanctioned != null)
				return false;
		} else if (!loanAmountSanctioned.equals(other.loanAmountSanctioned))
			return false;
		if (loanClosureDate == null) {
			if (other.loanClosureDate != null)
				return false;
		} else if (!loanClosureDate.equals(other.loanClosureDate))
			return false;
		if (monthlyPayment == null) {
			if (other.monthlyPayment != null)
				return false;
		} else if (!monthlyPayment.equals(other.monthlyPayment))
			return false;
		if (paymentStartDate == null) {
			if (other.paymentStartDate != null)
				return false;
		} else if (!paymentStartDate.equals(other.paymentStartDate))
			return false;
		if (termOfLoan == null) {
			if (other.termOfLoan != null)
				return false;
		} else if (!termOfLoan.equals(other.termOfLoan))
			return false;
		return true;
	}
	
	
}
