package yo.ask.sh;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 19:42
 * @Description:
 */
public class AskSHDo {
    @ExcelProperty(value = "股票代码")
    private String stockCode;
    @ExcelProperty(value = "公司名称")
    private String companyName;
    @ExcelProperty(value = "日期")
    private String date;
    @ExcelProperty(value = "问询函类型")
    private String type;
    @ExcelProperty(value = "pdf地址")
    private String pdf;
    @ExcelProperty(value = "问询函标题")
    private String title;
    @ExcelProperty(value = "关键字个数")
    private int keywords = 0;

    public AskSHDo() {

    }

    @Override
    public String toString() {
        return "AskSHDo{" +
                "stockCode='" + stockCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", pdf='" + pdf + '\'' +
                ", title='" + title + '\'' +
                ", keywords=" + keywords +
                '}';
    }

    public int getKeywords() {
        return keywords;
    }

    public void setKeywords(int keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
