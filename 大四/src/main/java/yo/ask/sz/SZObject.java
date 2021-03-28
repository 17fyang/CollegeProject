package yo.ask.sz;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 11:00
 * @Description:
 */
public class SZObject {
    @ExcelProperty(value = "公司代码")
    private String code;
    @ExcelProperty(value = "公司简称")
    private String company;
    @ExcelProperty(value = "发函日期")
    private String date;
    @ExcelProperty(value = "函件类别")
    private String type;
    @ExcelProperty(value = "函件内容")
    private String contentPdf;
    @ExcelProperty(value = "公司回复")
    private String replyPdf;
    @ExcelProperty(value = "关键词个数")
    private int keyWordTimes = 0;

    @Override
    public String toString() {
        return "SZObject{" +
                "code='" + code + '\'' +
                ", company='" + company + '\'' +
                ", date='" + date + '\'' +
                ", contentPdf='" + contentPdf + '\'' +
                ", replyPdf='" + replyPdf + '\'' +
                '}';
    }

    public int getKeyWordTimes() {
        return keyWordTimes;
    }

    public void setKeyWordTimes(int keyWordTimes) {
        this.keyWordTimes = keyWordTimes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContentPdf() {
        return contentPdf;
    }

    public void setContentPdf(String contentPdf) {
        this.contentPdf = contentPdf;
    }

    public String getReplyPdf() {
        return replyPdf;
    }

    public void setReplyPdf(String replyPdf) {
        this.replyPdf = replyPdf;
    }
}
