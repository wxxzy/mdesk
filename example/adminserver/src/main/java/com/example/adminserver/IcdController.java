package com.example.adminserver;

import com.alibaba.fastjson.JSONArray;
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
import org.springframework.util.StringUtils;
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

    final String dir = "D:\\data\\";
    final String filePath = "recipe.csv";

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
        int index = 1;
        try {
            File file =new File(filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            csvReader.readHeaders();
            Set<String> manual = new HashSet<>();
            while (csvReader.readRecord()) {
                String[] split = csvReader.get("after").split("\\;|\\ |\\,|\\；|\\，|\\、|\\；|\\‘|\\’|\\，|\\：|\\\\.|\\。");
                for (String s : split){
                    if(!StringUtils.isEmpty(s)){
                        manual.add(s);
                    }
                }

                index ++;
            }
            for (String str : manual){
                ManualDiagnoseModel manualDiagnoseModel = new ManualDiagnoseModel();
                manualDiagnoseModel.setDiagnose(str);
                manualDiagnoseDao.insert(manualDiagnoseModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success:" +index;
    }

    //数据数据与ICD10匹配结果
    @GetMapping(value = "/simnet")
    public String simnet(String level){
        String result = "success";
        if(StringUtils.isEmpty(level)){
            return "level不能为空，且只能为3,4,6";
        }
        if(!"346".contains(level)){
            return "level只能为3,4,6";
        }
        try {
            File file =new File(dir+filePath);
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"utf-8");
            CsvReader csvReader = new CsvReader(isr);
            csvReader.setSafetySwitch(false);
            // 读表头
            csvReader.readHeaders();

            //List<IcdModel> list = icdService.findAll();
            List<IcdModel> list = icdService.findIcdByLevel(level);
            List<String> liststr = icdService.findIcdByLevelStr(level);

            Map<String,Object> parm = new HashedMap<>();

            parm.put("use_gpu",true);
            parm.put("batch_size",1);

            String[] befer = new String[liststr.size()];
            String[] after = new String[liststr.size()];
            String[] score = new String[liststr.size()];
            // 读内容
            while (csvReader.readRecord()) {
                //log.info("第"+index+"条"+"，共"+count+"条"+",完成"+(index/count)*100+"%");
                Arrays.fill(befer, csvReader.get("befer"));
                Arrays.fill(after, csvReader.get("after"));
                Arrays.fill(score, csvReader.get("score"));
                //匹配参数
                Object texts[][]  = { befer, liststr.toArray()};
                parm.put("texts",texts);
                log.debug((new JSONObject(parm)).toJSONString());
                String resp = httpUtils.doPost("http://10.6.56.35:8866/predict/simnet_bow", new JSONObject(parm),100000);
                //log.info(resp);
                JSONObject json = (JSONObject) JSONObject.parse(resp);
                JSONArray array  = (JSONArray) json.get("results");
                for (int i = 0; i < array.size(); i++) {
                    JSONObject j = (JSONObject) array.get(i);
                    if(j.getLong("similarity") >= 0.8) {
                        log.info(j.toJSONString());
                        MatchSimnetBowModel matchModel = new MatchSimnetBowModel();
                        matchModel.setOldDiagnosis(j.getString("text_1"));
                        matchModel.setNewDiagnosis(j.getString("text_2"));
                        matchModel.setScore(j.getLong("similarity"));
                        IcdModel icdModel = list.get(i);
                        matchModel.setIcd10(icdModel.getCode());
                        matchModel.setManual(after[i]);
                        matchModel.setManualScore(Double.valueOf(score[i]));
                        matchSimnetBowService.insert(matchModel);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result;
    }
}
