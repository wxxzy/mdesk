package com.example.neo4j.controller;

import com.example.neo4j.dao.DiseaseRepository;
import com.example.neo4j.domain.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/2/13 18:11
 */
@RestController
@RequestMapping("/dd")
public class DDController {
    @Autowired
    DiseaseRepository diseaseRepository;

    @GetMapping("/get")
    public String relation() {
        Disease node = diseaseRepository.findByName("糖尿病");
        List<Long> ids = new ArrayList<>();
        ids.add(node.getId());
        Iterable<Disease> result = diseaseRepository.findAllById(ids, 1);
        return node.getPrevent();
    }
}
