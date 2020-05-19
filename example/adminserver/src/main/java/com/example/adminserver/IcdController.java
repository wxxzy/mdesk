package com.example.adminserver;

import com.alibaba.fastjson.JSONObject;
import com.csvreader.CsvReader;
import com.example.adminserver.automodify.ChineseStringMatcherStrtegy;
import com.example.adminserver.automodify.Context;
import com.example.adminserver.automodify.MatchingStrategy;
import com.example.adminserver.dao.*;
import com.example.adminserver.service.IcdService;
import com.example.adminserver.service.MatchService;
import com.example.adminserver.service.MatchSimnetBowService;
import com.example.adminserver.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    @Autowired
    private MatchSimnetBowService matchSimnetBowService;
    @Autowired
    private HttpUtils httpUtils;

    final String filePath = "D:\\workspaces\\java\\mdesks\\example\\adminserver\\src\\main\\resources\\recipe.csv";

    @GetMapping(value = "/findIcdByCode")
    public IcdModel findIcdByCode(String code){
        return icdService.findIcdByCode(code);
    }

    @GetMapping(value = "/all")
    public List<IcdModel> findAll(){
        return icdService.findAll();
    }

    //数据数据与ICD10匹配结果
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

    //人工匹配数据
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

    //数据数据与ICD10匹配结果
    @GetMapping(value = "/simnet")
    public String simnet(){
        String result = "";
        try {
            File file =new File(filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            // 读表头
            csvReader.readHeaders();

            //List<IcdModel> list = icdService.findAll();
            List<IcdModel> list3 = icdService.findIcdByLevel("3");
            List<IcdModel> list4 = icdService.findIcdByLevel("4");
            List<IcdModel> list6 = icdService.findIcdByLevel("6");

            List<String> liststr3 = icdService.findIcdByLevelStr("3");
            List<String> liststr4 = icdService.findIcdByLevelStr("4");
            List<String> liststr6 = icdService.findIcdByLevelStr("6");

            List<String> match = new ArrayList<>();
            Map<String,Object> parm = new HashedMap<>();

            parm.put("use_gpu",true);
            parm.put("batch_size",1);


            double count = 16147.0;

            double index =1;
            String[] befer = new String[list3.size()];
            // 读内容
            while (csvReader.readRecord()) {
                //log.info("第"+index+"条"+"，共"+count+"条"+",完成"+(index/count)*100+"%");

                Arrays.fill(befer, csvReader.get("befer"));

                /*for (IcdModel icdModel : list3){
                    MatchSimnetBowModel matchModel = new MatchSimnetBowModel();
                    matchModel.setOldDiagnosis(csvReader.get("befer"));
                    matchModel.setNewDiagnosis(icdModel.getDesc());
                    matchModel.setIcd10(icdModel.getCode());
                    matchModel.setScore(score);
                    matchModel.setManual(csvReader.get("after"));
                    matchModel.setManualScore(Double.valueOf(csvReader.get("score")));
                    matchSimnetBowService.insert(matchModel);
                }*/

                index++;
            }
            Object texts[][]  = { befer, liststr3.toArray()};
            parm.put("texts",texts);
            System.out.println((new JSONObject(parm)).toJSONString());
            result = httpUtils.doPost("http://10.6.56.35:8866/predict/simnet_bow", new JSONObject(parm),5000);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
