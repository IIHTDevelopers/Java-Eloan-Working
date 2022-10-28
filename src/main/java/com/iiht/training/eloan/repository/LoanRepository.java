package com.iiht.training.eloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	@Query(value="SELECT * FROM loan  Where customerId=:customerId", nativeQuery =true)
	public List<Loan> findLoanByCustomerId(@Param("customerId") Long customerId);
	
	@Query(value="SELECT * FROM loan  Where status=:status", nativeQuery =true)
	public List<Loan> findLoanByStatus(@Param("status") Integer status);

	@Query(value="SELECT * FROM loan  Where id=:loadId And status=:status", nativeQuery =true)
	public Loan findLoanByStatusAndId(@Param("loadId") Long loadId, @Param("status") Integer status);
}
