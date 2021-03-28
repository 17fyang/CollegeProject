package yo.keyword.sh;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/26 12:34
 * @Description:
 */
public class SHObject {
    private int page;
    private String company;
    private String title;
    private String http_url;
    private String net_time;
    private String month;
    private String stock_code;
    private String keyword_times;

    @Override
    public String toString() {
        return "SHObject{" +
                "page=" + page +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", http_url='" + http_url + '\'' +
                ", net_time='" + net_time + '\'' +
                ", month='" + month + '\'' +
                ", stock_code='" + stock_code + '\'' +
                ", keyword_times='" + keyword_times + '\'' +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public String getHttp_url() {
        return http_url;
    }

    public void setHttp_url(String http_url) {
        this.http_url = http_url;
    }

    public String getNet_time() {
        return net_time;
    }

    public void setNet_time(String net_time) {
        this.net_time = net_time;
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

    public String getKeyword_times() {
        return keyword_times;
    }

    public void setKeyword_times(String keyword_times) {
        this.keyword_times = keyword_times;
    }
}
