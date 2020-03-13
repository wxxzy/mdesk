package com.mungo.baidu.sound;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wangxingxiang
 * @Description
 * @date 2020/3/5 15:44
 */
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "9535228";
    public static final String API_KEY = "8YUMqQdzRPbKd4B77QvUqrxnEeA51nq0";
    public static final String SECRET_KEY = "ZyPQeYIyH1O40ytwDUWEgMfOWHHN6Y1N";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        //语速，取值0-9，默认为5中语速
        options.put("spd", "5");
        //音调，取值0-9，默认为5中语调
        options.put("pit", "5");
        //发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
        options.put("per", "0");
        //音量，取值0-15，默认为5中音量
        options.put("vol", "5");

        List<String> txtList = new ArrayList<>();
        txtList.add("大家好，给大家分享一款防止产后出血的常规用药——卡前列甲酯栓。\n");
        txtList.add("在70年代，我国将计划生育设定为基本国策，中国科学院药物研究所响应国家号召，开展了卡前列系列药物的研发，并由北京协和、北医一院开展临床试验试验方面工作，最终，开发出卡前列甲酯栓，由东北制药负责生产转化。并命名为卡孕栓。自此，从卡孕栓诞生，结束了我国全合成前列腺素的空白。1987年，卡孕栓获得了卫生部新药审评并取得一类新药证书。\n");
        txtList.add("幻灯中可以看到卡孕栓的产品外观。\n" +
                "卡孕栓的通用名为“卡前列甲酯栓”，是PGF2α的衍生物，我们在他的1位上增加一个“甲酯”结构、15位上增加一个“甲基”的结构，这样，使他的活性较母体化合物PGF2α强20倍左右，并且稳定性得到了大大的提高。保证较强的脂溶性，可以快速通过粘膜吸收。\n");
        txtList.add("卡孕栓用于产后出血预防及治疗，已被各类指南处方集推荐使用。\n" +
                "\n" +
                "包括2014年《中国妇产科杂志》，卡孕栓被列入产后出血预防和处理指南推荐用药，2012年版 《国家基本药物处方集》卡孕栓作为子宫收缩药被推荐使用，2010成人版\n" +
                "《中国国家处方集》卡孕栓作为产后出血的预防和治疗用药被推荐使用，同时也在2018版国家基本药物目录中。\n");
        txtList.add("卡孕栓产品的疗效和安全性得到临床实际应用的检验和认可，已经成为基层医疗单位的基本用药。但存在用药方法混乱等问题。为此《中国实用妇科与产科杂志》编辑部联手上海仁济医院林其德教授等众多妇产科专家研究和制定本共识，旨在为临床医生提供规范的用药指导和参考。\n");
        txtList.add("共识中介绍卡孕栓四大药理作用：首先，卡孕栓具有抗早孕作用，卡孕栓可以通过抑制孕酮分泌，间接起到抗早孕目的。第二点，卡孕栓促进子宫收缩作用，进而应用于计划生育的各种流产，基于此药理作用，卡孕栓 被广泛应用于产后止血，第三点，卡孕栓对胃肠、及膀胱平滑肌也有很好的促进收缩的作用，从而可以有效促进尿潴留以及术后肠排气。最后，卡孕栓可以减少宫颈羟脯氨酸的含量，激活弹性蛋白酶和胶原溶解酶，使子宫成熟，扩张，便于开展妇科宫腔镜以及计划生育科流产的应用。\n");
        txtList.add("卡孕栓起到外源性前列腺素作用引起平滑肌收缩，同时可以加强内源性前列腺素作用，使全子宫协调有力收缩。同时还可以引起胃肠道和膀胱平滑肌收缩，治疗术后尿潴留，促进肠排气\n");
        txtList.add("卡孕栓可以通过粘膜吸收进入循环系统，卡孕栓起效快，5分钟即可对在为子宫产生兴奋作用，5-20分钟即可达到峰值\n" +
                "卡孕栓在血中的半衰期为半小时，保证其在停药后血中药物浓度水平可迅速下降至对机体无反应的水平。\n" +
                "由于卡孕栓可以局部范围内加强内源性前列腺素的作用，因此可长时间使子宫收缩。\n");
        txtList.add("卡孕栓有以上三种给药方式。首先，阴道给药，这也是产品说明书中列出的方法，它的优势在于直接作用于靶向部位。方法是。如果阴道出现出血量过大的情况，卡孕栓可以通过直肠或舌下给药，这两种方法均也是由专家达成共识，直肠给药或舌下给药，\n" +
                "阴道给药后一定要手持药栓2分钟，待药栓溶解后退出手指，阴道给药不适合出血量大时使用，如果出血量大时可选择直肠给药和舌下给药两种方式，直肠给药也应注意待药栓溶解后再退出手指，以免影响作用效果。舌下给药要注意不能用于意识不清患者，防止误服。 并不能吞咽残渣\n");
        txtList.add("共识中卡孕栓在产科方面共有以下应用。预防治疗产后出血，着重于预防，减少风险，缩短第三产程、防治尿潴留，促进肠排气，子宫复旧\n");
        txtList.add("专家共识中共收录大量临床研究。检索到卡孕栓在预防产后出血24篇，治疗产后出血8篇，促进产后排气3篇，治疗尿潴留3篇\n");
        txtList.add("卡孕栓科有效预防产后出血，产后2小时减少出血量69.25ml，产后24小时减少出血量93.01ml，缩短第三产程3.52分钟\n");
        txtList.add("卡孕栓还可以联合缩宫素使用，效果优于单独使用缩宫素\n");
        txtList.add("卡孕栓在胎儿娩出后给药两枚\n" +
                "能有效预防产后出血，并缩短第三产程，\n");
        txtList.add("卡孕栓不仅可以预防产后出血，还可以单独使用治疗产后出血，其效果优于缩宫素\n");
        txtList.add("卡孕栓能够能够有效治疗产后出血，并缩短第三产程，应用于产后出血主要是在胎儿娩出后，10分钟后各给两枚，巩固给两枚，一共是应用6枚，3mg，最新所做的产后验证也显示，使用六枚卡孕栓非常安全，舌下给药，非常方便，适合翻身困难的患者，同时不会刺激唾液分泌，不用担心随唾液进入胃肠\n");
        txtList.add("纳入3项研究，1100例研究对象与不用药相比，卡孕栓可以使肠排气时间缩短16.48小时\n");
        txtList.add("可以有效防治尿潴留\n");
        txtList.add("在哈尔滨医科大学附属第一医院的临床观察中，\n" +
                "选取100例高危子宫复旧不良的剖宫产产妇，分为观察组和对照组。\n" +
                "结论：卡孕栓联合催产素应用，能够有效促进有子宫复旧不良的剖宫产产妇宫底下降，对促进子宫复旧有很好的效果。\n" +
                "综上所述，产后使用卡孕栓对减少产后出血、促进子宫复旧及对胃肠道的恢复效果明显。\n");
        txtList.add("众多国家级及省级专家推荐使用\n");
        txtList.add("卡孕栓能够显著使子宫平滑肌收缩，减少术后出血量，缩短第三产程，防治尿潴留，促进肠排气并促进子宫复旧，是预防、治疗产后出血的常规用药\n");
        txtList.add("卡孕栓主要的副作用就是一过性的恶心或呕吐，但症状非常轻，而且一过性的腹泻也可以帮助产妇促进肠排气\n" +
                "在诊断或治疗期间患者的血糖和血压较为正常的情况下，或者可以使用降糖降压药后使用卡孕栓。\n" +
                "前置胎盘剖宫产后仍然可以用卡孕栓\n");
        txtList.add("最后总结四点卡孕栓的优势\n" +
                "第一点，安全可靠，卡孕栓属于前列腺素类药物\n" +
                "第二点，预防产后出血，将风险降到最低\n" +
                "第三点，起效快，卡孕栓5分钟即可起效，同时由于内源性前列腺素的作用，其维持子宫收缩时间长达60分钟\n" +
                "第四点，卡孕栓性价比高，卡孕栓作为全球独家产品，国家1类新药，其原料药也是国家“六五”重点科技攻关项目。定价远远低于国外进口药物\n");
        txtList.add("感谢大家的观看\n");

        for (int i = 1; i < txtList.size()+1; i++) {
            System.out.println("正在合成：" + i + ":" +txtList.get(i-1));
            // 调用接口
            TtsResponse res = client.synthesis(txtList.get(i-1), "zh", 1, options);
            byte[] data = res.getData();
            JSONObject res1 = res.getResult();
            if (data != null) {
                try {
                    Util.writeBytesToFileSystem(data, "output"+i+".mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (res1 != null) {
                System.out.println(res1.toString(2));
            }

        }

    }

    public void synthesis(AipSpeech client) {
        //TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
        //System.out.println(res.getResult());

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "4");
        TtsResponse res = client.synthesis("你好百度", "zh", 1, options);
        System.out.println(res.getResult());
        JSONObject result = res.getResult();    //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        byte[] data = res.getData();            //生成的音频数据
    }
}
