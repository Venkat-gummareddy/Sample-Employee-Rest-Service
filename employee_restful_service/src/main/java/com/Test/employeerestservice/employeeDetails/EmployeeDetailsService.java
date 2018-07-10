package com.Test.employeerestservice.employeeDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Test.employeerestservice.common.ResourceNotFoundException;

import java.util.Optional;

@Service
public class EmployeeDetailsService {

    private EmployeeDetailsepository employeeDetRepository;

    @Autowired
    public EmployeeDetailsService(EmployeeDetailsepository employeeDetRepository) {
        this.employeeDetRepository = employeeDetRepository;
    }


    public Iterable<EmployeeDetails> findAllActive() {
        return employeeDetRepository.findAllByStatus(EmployeeDetailsStatus.ACTIVE);
    }

    public Optional<EmployeeDetails> findActiveById(long id) {
        return employeeDetRepository.findByStatusAndId(EmployeeDetailsStatus.ACTIVE, id);
    }

    public void deactivateById(long id) {
        Optional<EmployeeDetails> employee = employeeDetRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setStatus(EmployeeDetailsStatus.INACTIVE);
            employeeDetRepository.save(employee.get());
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public EmployeeDetails save(EmployeeDetails employee) {
        return employeeDetRepository.save(employee);
    }
}
