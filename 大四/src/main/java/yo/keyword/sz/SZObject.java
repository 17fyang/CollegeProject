package yo.keyword.sz;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 13:30
 * @Description:
 */
public class SZObject {
    private int id;
    private int pages;
    private String company;
    private String title;
    private String pdf_location;
    private String date;
    private String month;
    private String stock_code;
    private String company_2021;
    private int keyword_times;

    @Override
    public String toString() {
        return "SZObject{" +
                "id=" + id +
                ", pages=" + pages +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", pdf_location='" + pdf_location + '\'' +
                ", date='" + date + '\'' +
                ", month='" + month + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", company_2021='" + company_2021 + '\'' +
                ", keyword_times=" + keyword_times +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPdf_location() {
        return pdf_location;
    }

    public void setPdf_location(String pdf_location) {
        this.pdf_location = pdf_location;
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

    public String getStock_code() {
        return stock_code;
    }

    public void setStock_code(String stock_code) {
        this.stock_code = stock_code;
    }

    public String getCompany_2021() {
        return company_2021;
    }

    public void setCompany_2021(String company_2021) {
        this.company_2021 = company_2021;
    }

    public int getKeyword_times() {
        return keyword_times;
    }

    public void setKeyword_times(int keyword_times) {
        this.keyword_times = keyword_times;
    }
}
