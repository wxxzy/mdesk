package com.example.adminserver;

import com.csvreader.CsvReader;
import com.example.adminserver.automodify.ChineseStringMatcherStrtegy;
import com.example.adminserver.automodify.Context;
import com.example.adminserver.automodify.MatchingStrategy;
import com.example.adminserver.dao.IcdModel;
import com.example.adminserver.dao.ManualDiagnoseDao;
import com.example.adminserver.dao.ManualDiagnoseModel;
import com.example.adminserver.dao.MatchModel;
import com.example.adminserver.service.IcdService;
import com.example.adminserver.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/icd")
@Slf4j
public class IcdController {
    @Autowired
    private IcdService icdService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private ManualDiagnoseDao manualDiagnoseDao;

    final String filePath = "D:\\workspaces\\java\\mdesks\\example\\adminserver\\src\\main\\resources\\recipe.csv";

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
        //System.out.println(((ChineseStringMatcherStrtegy) matchingStrategy1).sim());
        try {
            File file =new File(filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            // 读表头
            csvReader.readHeaders();

            List<IcdModel> list = icdService.findAll();


            double count = 16147.0;

            double index =1;
            // 读内容
            while (csvReader.readRecord()) {
                log.info("第"+index+"条"+"，共"+count+"条"+",完成"+(index/count)*100+"%");

                // 读该行的某一列
                //String befer = csvReader.get("befer");

                for (IcdModel icdModel : list){
                    MatchingStrategy matchingStrategy1 = new ChineseStringMatcherStrtegy(csvReader.get("befer"),icdModel.getDesc());
                    Context context = new Context(matchingStrategy1);
                    ((ChineseStringMatcherStrtegy) matchingStrategy1).setMatchString(icdModel.getDesc());
                    context.changeStrategy(matchingStrategy1);
                    context.executeStrategy();

                    double score = ((ChineseStringMatcherStrtegy) matchingStrategy1).sim();

                    MatchModel matchModel = new MatchModel();
                    matchModel.setOldDiagnosis(csvReader.get("befer"));
                    matchModel.setNewDiagnosis(icdModel.getDesc());
                    matchModel.setIcd10(icdModel.getCode());
                    matchModel.setScore(score);
                    matchModel.setManual(csvReader.get("after"));
                    matchModel.setManualScore(Double.valueOf(csvReader.get("score")));
                    matchService.insert(matchModel);
                }

                // 读一整行
                //String line = csvReader.getRawRecord();

                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping(value = "/manual")
    public String manual(){
        try {
            File file =new File(filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            csvReader.readHeaders();
            Set<String> manual = new HashSet<>();
            while (csvReader.readRecord()) {
                manual.add(csvReader.get("after"));
            }
            for (String str : manual){
                ManualDiagnoseModel manualDiagnoseModel = new ManualDiagnoseModel();
                manualDiagnoseModel.setDiagnose(str);
                manualDiagnoseDao.insert(manualDiagnoseModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }
}
