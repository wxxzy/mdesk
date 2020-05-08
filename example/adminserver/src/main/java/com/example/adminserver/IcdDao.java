package com.example.adminserver;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface  IcdDao {
    @SelectProvider(type = IcdMapper.class,method = "findIcdByCode")
    IcdModel findIcdByCode(String code);

    // 插入 并查询id 赋给传入的对象
    @Insert("INSERT INTO icd10(code,codeext  ,level,desc) VALUES(#{code}, #{codeext}, #{level}, #{desc})")
    @SelectKey(statement = "SELECT seq id FROM sqlite_sequence WHERE (name = 'icd10')",
            before = false, keyProperty = "id", resultType = int.class)
    int insert(IcdModel model);
}
