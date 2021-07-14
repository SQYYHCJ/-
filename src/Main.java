import com.alibaba.fastjson.JSON;
import top.sqyy.entity.JsonRootBean;
import top.sqyy.util.SubTitleUtil;
import top.sqyy.util.TransApi;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer

    public static void main(String[] args) {
        boolean flag = SubTitleUtil.translator("F:\\TranslatorTest\\src\\input.srt","F:\\TranslatorTest\\src\\output.srt", new String[]{"<i>", "</i>"},"en","zh");
        if (flag){
            System.out.println("翻译完成");
        }

    }

}
