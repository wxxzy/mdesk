package com.example.neo4j.dao;

import com.example.neo4j.domain.Disease;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/13 17:46
 */
@Repository
public interface  DiseaseRepository extends Neo4jRepository<Disease,Long>{
    Disease findByName(@Param("name") String name);
}
