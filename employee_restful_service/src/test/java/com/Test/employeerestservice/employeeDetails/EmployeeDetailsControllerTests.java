package com.Test.employeerestservice.employeeDetails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.Test.employeerestservice.employeeDetails.EmployeeDetails;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsController;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsService;
import com.Test.employeerestservice.employeeDetails.EmployeeDetailsStatus;

import java.util.Arrays;
import java.util.List;

import static com.Test.employeerestservice.employeeDetails.EmployeeTestUtils.makeEmployee;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeDetailsControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeDetailsController employeeDetController;

    @MockBean
    private EmployeeDetailsService employeeDetService;

    @Test
    public void getAllActiveEmployeesDetails_respondsWithEmployeesDetails() throws Exception {
        EmployeeDetails empDet1 = makeEmployee("Venkat", EmployeeDetailsStatus.ACTIVE);
        EmployeeDetails empDet2 = makeEmployee("Gummareddy", EmployeeDetailsStatus.ACTIVE);

        List<EmployeeDetails> activeEmployeesList = Arrays.asList(empDet1, empDet2);

        given(employeeDetController.getAllActiveEmployees()).willReturn(activeEmployeesList);

        mvc.perform(get("/employees")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].FirstName", is(empDet1.getFirstName())))
                .andExpect(jsonPath("$[1].FirstName", is(empDet2.getFirstName())));
    }

    @Test
    @WithMockUser
    public void deactivateEmployeeDetails_callsEmployeeDetailsServiceDeactivateById() throws Exception {
        mvc.perform(delete("/employees/123"));
        verify(employeeDetService, times(1)).deactivateById(123L);
    }
}
