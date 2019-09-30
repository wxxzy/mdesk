package com.example.es;


import com.example.es.domain.DepartmentDO;
import com.example.es.domain.EmployeesDO;
import com.example.es.repository.DepartmentRepository;
import com.example.es.repository.EmployeesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class ElasticTest {

    @Autowired
    EmployeesRepository employeesRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void Test() {
        EmployeesDO employeesDO = new EmployeesDO();
        employeesDO.setEmpNo(1234);
        employeesDO.setFirstName("Tom");
        employeesDO.setLastName("jery");
        employeesDO.setGender("M");
        employeesRepository.save(employeesDO);

        DepartmentDO departmentDO = new DepartmentDO();
        departmentDO.setDeptNo(1);
        departmentDO.setDeptName("开发部");
        departmentRepository.save(departmentDO);
    }
}

