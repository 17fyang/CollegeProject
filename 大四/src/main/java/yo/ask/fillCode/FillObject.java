package yo.ask.fillCode;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/27 15:54
 * @Description:
 */
public class FillObject {
    @ExcelProperty(value = "标题")
    private String title;
    @ExcelProperty(value = "pdf文件地址")
    private String pdf;
    @ExcelProperty(value = "日期")
    private String date;
    @ExcelProperty(value = "月份")
    private String month;
    @ExcelProperty(value = "股票代码")
    private String code;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
