package com.example.adminserver;

import com.csvreader.CsvReader;
import com.example.adminserver.dao.IcdModel;
import com.example.adminserver.service.IcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/icd")
public class IcdController {
    @Autowired
    private IcdService icdService;

    @GetMapping(value = "/findIcdByCode")
    public IcdModel findIcdByCode(String code){
        return icdService.findIcdByCode(code);
    }

    @GetMapping(value = "/all")
    public List<IcdModel> findAll(){
        return icdService.findAll();
    }

    @GetMapping(value = "/match")
    public String match(){
        String filePath = "D:\\workspaces\\java\\mdesks\\example\\adminserver\\src\\main\\resources\\recipe.csv";
        try {
            File file =new File(filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            // 读表头
            csvReader.readHeaders();

            // 读内容
            while (csvReader.readRecord()) {
                // 读一整行
                System.out.println(csvReader.getRawRecord());
                // 读该行的某一列
                //System.out.println(csvReader.get("befer"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
