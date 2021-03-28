package lessonCrawer;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 14:10
 * @Description:
 */
public class MoocCapture {
    private String name = "";
    private String link = "";
    private List<MoocCapture> son = new ArrayList<>();

    public static MoocCapture valueOf(Element element) {
        MoocCapture capture = new MoocCapture(element.getElementsByTag("h3").text());

        Elements elements = element.getElementsByTag("ul").first().getElementsByTag("li");
        List<MoocCapture> list = new ArrayList<>();
        for (Element e : elements) {
            Elements buttonElements = e.getElementsByTag("button");
            if (buttonElements != null) buttonElements.remove();
            list.add(new MoocCapture(e.text()));
        }
        capture.son = list;
        return capture;
    }

    public static ArrayNode listToJson(List<MoocCapture> list) {
        ArrayNode arrayNode = JsonUtil.getObjectMapper().createArrayNode();
        for (MoocCapture capture : list) {
            ObjectNode objectNode = JsonUtil.getObjectMapper().createObjectNode();
            objectNode.put("name", capture.getName());
            objectNode.put("link", capture.getLink());
            objectNode.put("son", MoocCapture.listToJson(capture.getSon()));
            arrayNode.add(objectNode);
        }
        return arrayNode;
    }

    public MoocCapture(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MoocCapture> getSon() {
        return son;
    }

    public void setSon(List<MoocCapture> son) {
        this.son = son;
    }
}
