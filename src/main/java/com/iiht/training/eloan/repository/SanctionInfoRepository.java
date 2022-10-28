package com.iiht.training.eloan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.SanctionInfo;

@Repository
public interface SanctionInfoRepository extends JpaRepository<SanctionInfo, Long>{
	
	@Query(value="SELECT  FROM sanction_info  where loan_app_id=:loanId", nativeQuery =true)
	public SanctionInfo findSanctionInfoByLoanId(@Param("loanId") Long loanId);

}
