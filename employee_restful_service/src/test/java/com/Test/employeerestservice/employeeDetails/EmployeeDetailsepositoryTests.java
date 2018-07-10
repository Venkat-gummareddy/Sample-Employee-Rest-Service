package com.Test.employeerestservice.employeeDetails;

import org.assertj.core.util.IterableUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Test.employeerestservice.employeeDetails.EmployeeDetails;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsepository;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsStatus;

import java.util.Collection;

import static com.Test.employeerestservice.employeeDetails.EmployeeTestUtils.makeEmployee;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeDetailsepositoryTests {

    @Autowired
    private TestEntityManager testentimngr;

    @Autowired
    private EmployeeDetailsepository employeeDetailsRepository;

    @Test
    public void findAllByStatus_returnsOnlyEmployeesWithGivenStatus() {        
    	testentimngr.persist(makeEmployee("Venkat", EmployeeDetailsStatus.ACTIVE));
    	testentimngr.persist(makeEmployee("Gummareddy", EmployeeDetailsStatus.ACTIVE));
    	testentimngr.persist(makeEmployee("Ram", EmployeeDetailsStatus.ACTIVE));
    	testentimngr.persist(makeEmployee("Naveen", EmployeeDetailsStatus.INACTIVE));
    	testentimngr.persist(makeEmployee("Krish", EmployeeDetailsStatus.INACTIVE));
    	testentimngr.flush();
        
        Collection<EmployeeDetails> employeesCollectn = IterableUtil.toCollection(
        		employeeDetailsRepository.findAllByStatus(EmployeeDetailsStatus.INACTIVE)
        );
        
        assertThat(employeesCollectn.size()).isEqualTo(2);
        for (EmployeeDetails employee : employeesCollectn) {
            assertThat(EmployeeDetailsStatus.INACTIVE.equals(employee.getStatus()));
        }
    }
}
