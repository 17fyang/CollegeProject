package yo.domain;

/**
 * ClassName: ask_texxt
 * Description:
 * date: 2020/10/5 17:03
 *
 * @author :涔岄甫鍧愰鏈轰籂
 * @version:
 */
public class ask_text {
    private int id;
    private int page;
    private String company;
    private String title;
    private String http_url;
    private String net_time;
    private int downloadFlag;

    public ask_text() {
    }

    public ask_text(int id, int page, String company, String title, String http_url, String net_time, int downloadFlag) {
        this.id = id;
        this.page = page;
        this.company = company;
        this.title = title;
        this.http_url = http_url;
        this.net_time = net_time;
        this.downloadFlag = downloadFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(int downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    @Override
    public String toString() {
        return "ask_text{" +
                "id=" + id +
                ", page=" + page +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", http_url='" + http_url + '\'' +
                ", net_time='" + net_time + '\'' +
                ", downloadFlag=" + downloadFlag +
                '}';
    }
}
