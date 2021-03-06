package com.Test.employeerestservice.employeeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value="ID", access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank
    @Size(max = 50)
    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("MiddleInitial")
    private Character middleInitial;

    @Size(max = 50)
    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("DateOfBirth")
    private LocalDate dateOfBirth;

    @JsonProperty("DateOfEmployment")
    private LocalDate dateOfEmployment;

    @Enumerated(EnumType.STRING)
    @JsonProperty(value="Status", access = JsonProperty.Access.READ_ONLY)
    private EmployeeDetailsStatus status = EmployeeDetailsStatus.ACTIVE;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Character getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(Character middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public EmployeeDetailsStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeDetailsStatus status) {
        this.status = status;
    }
}
