package com.example.es.repository;

import com.example.es.domain.EmployeesDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface  EmployeesRepository extends ElasticsearchRepository<EmployeesDO,Integer> {
}
