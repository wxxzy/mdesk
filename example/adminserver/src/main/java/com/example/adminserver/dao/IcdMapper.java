package com.example.adminserver.dao;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class IcdMapper {

    public String findIcdByCode(String code){
        SQL sql = new SQL();
        sql.SELECT("code,codeExt,desc,level");
        sql.FROM("t_icd10");
        if(!StringUtils.isEmpty(code)){
            sql.WHERE("code=#{code}");
        }
        return sql.toString();
    }

    public String findIcdByLevel(String level){
        SQL sql = new SQL();
        sql.SELECT("code,codeExt,desc,level");
        sql.FROM("t_icd10");
        if(!StringUtils.isEmpty(level)){
            sql.WHERE("level=#{level}");
        }
        return sql.toString();
    }

    public String findAll(){
        SQL sql = new SQL();
        sql.SELECT("code,codeExt,desc,level");
        sql.FROM("t_icd10");
        return sql.toString();
    }
}
