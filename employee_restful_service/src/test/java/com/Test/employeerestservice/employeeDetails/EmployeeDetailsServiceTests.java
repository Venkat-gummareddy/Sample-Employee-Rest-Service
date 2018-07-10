package com.Test.employeerestservice.employeeDetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.Test.employeerestservice.employeeDetails.EmployeeDetails;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsepository;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsService;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsStatus;

import java.util.Optional;

import static com.Test.employeerestservice.employeeDetails.EmployeeTestUtils.makeEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDetailsServiceTests {

    @Autowired
    private EmployeeDetailsService employeeService;

    @MockBean
    private EmployeeDetailsepository employeeRepository;

    @Test
    public void deactivateById_setsEmployeeStatusToInactive() {      
        EmployeeDetails activeEmployee = makeEmployee("John", EmployeeDetailsStatus.ACTIVE);
        activeEmployee.setId(1L);
        when(employeeRepository.findById(activeEmployee.getId()))
            .thenReturn(Optional.of(activeEmployee));
      
        employeeService.deactivateById(activeEmployee.getId());
        EmployeeDetails deactivatedEmployee = employeeRepository.findById(activeEmployee.getId()).get();
       
        assertThat(deactivatedEmployee.getStatus()).isEqualTo(EmployeeDetailsStatus.INACTIVE);
    }
}
