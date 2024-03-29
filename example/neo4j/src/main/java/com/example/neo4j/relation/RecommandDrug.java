package com.example.neo4j.relation;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/13 18:07
 */
@RelationshipEntity(type = "recommand_drug")
public class RecommandDrug {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
