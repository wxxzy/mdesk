package com.example.es.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;


@Document(indexName = "company",type = "employe" , shards = 8, replicas = 1)
public class EmployeesDO implements Serializable {
    private static final long serialVersionUID = -5486342676464419079L;
    @Id
    private Integer empNo;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private Date hireDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "EmployeesDO [empNo=" + empNo + ", " +
                "birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
                + lastName + ", gender=" + gender + ", hireDate=" + hireDate + "]";
    }

}
