package top.sqyy.entity;

/**
 * @Classname SrtSubtitle
 * @Created by HCJ
 * @Date 2021/7/13 20:28
 */
public class SrtSubtitle {
    private Integer id;
    private String startTime;
    private String endTime;
    private String subTitleOne;
    private String subTitleTwo;

    public SrtSubtitle() {
    }

    public SrtSubtitle(Integer id, String startTime, String endTime, String subTitleOne, String subTitleTwo) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subTitleOne = subTitleOne;
        this.subTitleTwo = subTitleTwo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSubTitleOne() {
        return subTitleOne;
    }

    public void setSubTitleOne(String subTitleOne) {
        this.subTitleOne = subTitleOne;
    }
    public void addSubTitleOne(String item){
        this.subTitleOne += item;
    }

    public String getSubTitleTwo() {
        return subTitleTwo;
    }

    public void setSubTitleTwo(String subTitleTwo) {
        this.subTitleTwo = subTitleTwo;
    }

    @Override
    public String toString() {
        StringBuffer buffer= new StringBuffer();
        buffer.append(id+"\n");
        buffer.append(startTime+" --> "+ endTime+"\n");
        buffer.append(subTitleOne);
        buffer.append("\n");
        if (subTitleTwo!=null&&subTitleTwo!=""){
            buffer.append(subTitleTwo);
            buffer.append("\n");
        }


        return String.valueOf(buffer);
    }
}
