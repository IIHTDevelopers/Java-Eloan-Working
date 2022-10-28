package com.iiht.training.eloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.ProcessingInfo;

@Repository
public interface ProcessingInfoRepository extends JpaRepository<ProcessingInfo, Long>{
	
	@Query(value="SELECT * FROM processing_info  where loan_app_id=:loanId", nativeQuery =true)
	public ProcessingInfo findByLoanId(@Param("loanId") Long loanId);

}
