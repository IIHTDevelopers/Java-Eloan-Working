package com.iiht.training.eloan.model;

public class LoanOutputDto {
	private Long customerId;
	private Long loanAppId;
	private UserDto userDto;
	private LoanDto loanDto;
	private ProcessingDto processingDto;
	private SanctionOutputDto sanctionOutputDto;
	private String status;
	private String remark;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getLoanAppId() {
		return loanAppId;
	}
	public void setLoanAppId(Long loanAppId) {
		this.loanAppId = loanAppId;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public LoanDto getLoanDto() {
		return loanDto;
	}
	public void setLoanDto(LoanDto loanDto) {
		this.loanDto = loanDto;
	}
	public ProcessingDto getProcessingDto() {
		return processingDto;
	}
	public void setProcessingDto(ProcessingDto processingDto) {
		this.processingDto = processingDto;
	}
	public SanctionOutputDto getSanctionOutputDto() {
		return sanctionOutputDto;
	}
	public void setSanctionOutputDto(SanctionOutputDto sanctionOutputDto) {
		this.sanctionOutputDto = sanctionOutputDto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "LoanOutputDto [customerId=" + customerId + ", loanAppId=" + loanAppId + ", userDto=" + userDto
				+ ", loanDto=" + loanDto + ", processingDto=" + processingDto + ", sanctionOutputDto="
				+ sanctionOutputDto + ", status=" + status + ", remark=" + remark + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((loanAppId == null) ? 0 : loanAppId.hashCode());
		result = prime * result + ((loanDto == null) ? 0 : loanDto.hashCode());
		result = prime * result + ((processingDto == null) ? 0 : processingDto.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((sanctionOutputDto == null) ? 0 : sanctionOutputDto.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userDto == null) ? 0 : userDto.hashCode());
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
		LoanOutputDto other = (LoanOutputDto) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (loanAppId == null) {
			if (other.loanAppId != null)
				return false;
		} else if (!loanAppId.equals(other.loanAppId))
			return false;
		if (loanDto == null) {
			if (other.loanDto != null)
				return false;
		} else if (!loanDto.equals(other.loanDto))
			return false;
		if (processingDto == null) {
			if (other.processingDto != null)
				return false;
		} else if (!processingDto.equals(other.processingDto))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (sanctionOutputDto == null) {
			if (other.sanctionOutputDto != null)
				return false;
		} else if (!sanctionOutputDto.equals(other.sanctionOutputDto))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userDto == null) {
			if (other.userDto != null)
				return false;
		} else if (!userDto.equals(other.userDto))
			return false;
		return true;
	}
	
}
