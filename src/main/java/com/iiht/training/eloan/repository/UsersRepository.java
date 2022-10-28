package com.iiht.training.eloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iiht.training.eloan.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	
	@Query(value="SELECT u FROM Users u Where u.role=:role")
	public List<Users> findAllUsersBasedOnRole(@Param("role") String role);
	
	@Query(value="SELECT u FROM Users u Where u.id=:id and u.role=:role")
	public Users findCustomerById(@Param("role") String role, @Param("id") Long id);
	
	

}
