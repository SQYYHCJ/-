/**
 * Copyright 2021 json.cn
 */
package top.sqyy.entity;
import java.util.List;

/**
 * Auto-generated: 2021-07-14 12:17:54
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private String from;
    private String to;
    private List<Trans_result> trans_result;
    public void setFrom(String from) {
        this.from = from;
    }
    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }
    public String getTo() {
        return to;
    }

    public void setTrans_result(List<Trans_result> trans_result) {
        this.trans_result = trans_result;
    }
    public List<Trans_result> getTrans_result() {
        return trans_result;
    }

}