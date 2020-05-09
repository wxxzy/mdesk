package com.example.adminserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UploadController {

    @Value("${userName}")
    private String userName;
    @Value("${bookTitle}")
    private String bookTitle;

    @Autowired
    private BatchImportService batchImportService;

    private String result = "";

    private static final String filePath="D:/data/downloads/";

    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", userName);
        map.addAttribute("bookTitle", bookTitle);
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }

    @GetMapping("/upload")
    public String index(Model modelMap){
        modelMap.addAttribute("result", result);
        return "upload";
    }

    @RequestMapping(value="/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model modelMap){
        if(file.isEmpty()){
            result= "文件为空";
            modelMap.addAttribute("result", result);
            return "文件为空";
        }


        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名称为》》》,{}",fileName);
            //设置文件存储路径
            String path=filePath+fileName;
            File dest = new File(path);
            //检测是否存在目录
            if(!dest.getParentFile().exists()){
                //新建文件夹
                dest.getParentFile().mkdirs();
            }
            //批量导入
            String message = batchImportService.batchImport(fileName,file,"admin");

            //file.transferTo(dest);
            result= "上传成功"+message;
            modelMap.addAttribute("result", result);
            return "upload";
        } catch (Exception e) {
            e.printStackTrace();
        }
        result= "上传失败";
        modelMap.addAttribute("result", result);
        return  "upload";
    }


    @RequestMapping("/upload2")
    @ResponseBody
    public List<Object> pubggupload(@RequestParam("file") MultipartFile file) throws Exception {
        String name=file.getOriginalFilename();
        if(name.length()<6|| !name.substring(name.length()-5).equals(".xlsx")){
            List<Object>li=new ArrayList<>();
            li.add("文件格式错误");
            return li;
        }
        List<Object>list = ExcelUtils.excelToShopIdList(file.getInputStream());

        return list;

    }
}
