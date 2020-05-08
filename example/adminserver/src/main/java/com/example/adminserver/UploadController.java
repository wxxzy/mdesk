package com.example.adminserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadController {

    @Value("${userName}")
    private String userName;
    @Value("${bookTitle}")
    private String bookTitle;


    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", userName);
        map.addAttribute("bookTitle", bookTitle);
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "welcome";
    }

    @RequestMapping("/upload")
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
