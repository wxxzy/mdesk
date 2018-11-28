package com.example.es.repository;

import com.example.es.domain.DepartmentDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DepartmentRepository extends ElasticsearchRepository<DepartmentDO, Integer> {
}
