package com.stacksimplify.restservices.repositories;

import com.stacksimplify.restservices.entity.EmployeeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeManagement, Long> {

}
