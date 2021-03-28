package lessonCrawer;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @Author: 乌鸦坐飞机亠
 * @CreateDate: 2020/11/12 10:01
 * @Version: 1.0
 * @Description: Restful格式返回封装类
 */
public class Rest {
    private int code;
    private String msg;
    private JsonNode data;

    public Rest(int code, String msg, JsonNode data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Rest{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }
}