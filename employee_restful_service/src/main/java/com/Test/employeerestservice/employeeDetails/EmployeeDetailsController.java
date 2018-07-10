package com.Test.employeerestservice.employeeDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Test.employeerestservice.common.ResourceNotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeDetailsController {

    private EmployeeDetailsService employeeDetailsService;

    @Autowired
    public EmployeeDetailsController(EmployeeDetailsService employeeService) {
        this.employeeDetailsService = employeeService;
    }

    @GetMapping
    public Iterable<EmployeeDetails> getAllActiveEmployees() {
        return employeeDetailsService.findAllActive();
    }

    @GetMapping("{id}")
    public EmployeeDetails getActiveEmployee(@PathVariable long id) {
        Optional<EmployeeDetails> employee = employeeDetailsService.findActiveById(id);

        if (!employee.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return employee.get();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateEmployee(@PathVariable long id) {
        try {
        	employeeDetailsService.deactivateById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDetails> createEmployee(@RequestBody EmployeeDetails employee) {
        EmployeeDetails savedEmployee = employeeDetailsService.save(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).body(savedEmployee);
    }

    @PutMapping("{id}")
    public EmployeeDetails updateActiveEmployee(@PathVariable long id, @RequestBody EmployeeDetails employeeDet) {
        Optional<EmployeeDetails> existingEmployeeDetails = employeeDetailsService.findActiveById(id);

        if (existingEmployeeDetails.isPresent()) {
        	employeeDet.setId(existingEmployeeDetails.get().getId());
        	employeeDet.setStatus(existingEmployeeDetails.get().getStatus());
            return employeeDetailsService.save(employeeDet);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
