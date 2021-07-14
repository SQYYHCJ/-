package top.sqyy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import top.sqyy.entity.JsonRootBean;
import top.sqyy.entity.SrtSubtitle;
import top.sqyy.entity.Trans_result;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Classname SubTitleUtil
 * @Created by HCJ
 * @Date 2021/7/13 20:33
 */
public class SubTitleUtil {

    private  static BufferedReader br = null;
    private  static BufferedWriter bw = null;
    private  static FileReader reader;
    private  static FileWriter writer;
    private  static String idExpression = "[0-9]+";
    private  static String timeExpression="[0-9][0-9]:[0-5][0-9]:[0-5][0-9],[0-9][0-9][0-9] --> [0-9][0-9]:[0-5][0-9]:[0-5][0-9],[0-9][0-9][0-9]";
    private static TransApi api=new TransApi("","你的密码");

    public static BufferedReader getBr(String brAddress){
        try {
            reader = new FileReader(brAddress);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br = new BufferedReader(reader);
    }

    public static BufferedWriter getBw(String bwAddress){

        try {
            writer = new FileWriter(bwAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bw = new BufferedWriter(writer);
    }


    public static List<SrtSubtitle> getSrtSubtitle(String location){
        br = getBr(location);
        String line;
        Integer id = 0;
        String startTime="",endTime="";
        List<SrtSubtitle> srtSubtitles=new ArrayList<>();
        String details="";
        try {
            while ((line = br.readLine())!=null){

                if (!"".equals(line)){
                    if (Pattern.matches(idExpression,line)){
//                    匹配为标号

                        id = Integer.valueOf(line);

                    }else if (Pattern.matches(timeExpression,line)){
//                    匹配为时间
                        startTime = line.substring(0,12);
                        endTime = line.substring(17,29);
                    }else {
                        details += line;
                    }
                }
                if ("".equals(line)){
//                   匹配为空行
                    SrtSubtitle srtSubtitle = new SrtSubtitle(id,startTime,endTime,details,"");
                    srtSubtitles.add(srtSubtitle);
                    details = "";

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return srtSubtitles;

    }
    public static List<SrtSubtitle> cutSubTitleList(List<SrtSubtitle> srtSubtitles, String[] cut){
        List<SrtSubtitle> newSrtSubtitles = new ArrayList<>();
        for (SrtSubtitle subtitle:
             srtSubtitles) {
            for (String s:cut
                 ) {
                subtitle.setSubTitleOne(subtitle.getSubTitleOne().replace(s,""));
            }
            newSrtSubtitles.add(subtitle);
        }
        System.out.println("提取完成");
        return newSrtSubtitles;
    }

    public static SrtSubtitle cutSubTitle(SrtSubtitle srtSubtitle, String[] cut){
        for (String s :
                cut) {
            srtSubtitle.setSubTitleOne(srtSubtitle.getSubTitleOne().replace(s,""));
        }
        return srtSubtitle;
    }

    public static boolean transform(List<SrtSubtitle> srtSubtitles, String from, String to, String dsc){
        boolean flag = true;
        for (SrtSubtitle subtitle:
             srtSubtitles) {
            String json = api.getTransResult(subtitle.getSubTitleOne(),from,to);
            JsonRootBean object = JSON.parseObject(json,new TypeReference<JsonRootBean>(){});
            Trans_result result = object.getTrans_result().get(0);
            if (result !=null){
                subtitle.setSubTitleTwo(result.getDst());
            }

        }
        bw = getBw(dsc);
        int a = srtSubtitles.size();
        int b = 0;
        for (SrtSubtitle s:
             srtSubtitles) {
            try {
                String in = s.toString();
                bw.write(in);
                bw.newLine();
                b++;
                if (b%50==0){
                    System.out.println("已写入"+b+"条"+"总共"+a+"条");
                }
            } catch (IOException e) {
                e.printStackTrace();
                flag = false;

            }finally {
                try {
                    bw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

       return flag;
    }

    public  static boolean translator (String before,String after,String[] cut,String from,String to){
        return transform(cutSubTitleList(getSrtSubtitle(before),cut),from,to,after);
    }
}
