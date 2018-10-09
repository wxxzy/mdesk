package com.example.es.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;


@Document(indexName = "department",type = "department" , shards = 8, replicas = 1)
public class DepartmentDO implements Serializable {
    private static final long serialVersionUID = -5486342676464419079L;
    @Id
    private Integer deptNo;
    private String deptName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
