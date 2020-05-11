package com.example.adminserver;

import com.csvreader.CsvReader;
import com.example.adminserver.automodify.ChineseStringMatcherStrtegy;
import com.example.adminserver.automodify.Context;
import com.example.adminserver.automodify.MatchingStrategy;
import com.example.adminserver.dao.IcdModel;
import com.example.adminserver.service.IcdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping(value = "/icd")
@Slf4j
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

        //System.out.println(((ChineseStringMatcherStrtegy) matchingStrategy1).sim());
        try {
            File file =new File(filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            // 读表头
            csvReader.readHeaders();

            List<IcdModel> list = icdService.findAll();
            MatchingStrategy matchingStrategy1 = new ChineseStringMatcherStrtegy("","");
            Context context = new Context(matchingStrategy1);
            // 读内容
            while (csvReader.readRecord()) {
                // 读该行的某一列
                //String befer = csvReader.get("befer");

                ((ChineseStringMatcherStrtegy) matchingStrategy1).setOriginString(csvReader.get("befer"));



                for (IcdModel icdModel : list){
                    ((ChineseStringMatcherStrtegy) matchingStrategy1).setMatchString(icdModel.getDesc());
                    context.changeStrategy(matchingStrategy1);
                    context.executeStrategy();

                    double score = ((ChineseStringMatcherStrtegy) matchingStrategy1).sim();

                }

                // 读一整行
                String line = csvReader.getRawRecord();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
