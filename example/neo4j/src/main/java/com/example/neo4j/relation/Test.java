package com.example.neo4j.relation;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/28 18:00
 */
@RelationshipEntity(type = "tttt")
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
