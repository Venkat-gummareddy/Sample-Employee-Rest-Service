package com.Test.employeerestservice.employeeDetails;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDetailsepository extends CrudRepository<EmployeeDetails, Long> {
    Optional<EmployeeDetails> findByStatusAndId(EmployeeDetailsStatus status, long id);
    Iterable<EmployeeDetails> findAllByStatus(EmployeeDetailsStatus status);
}