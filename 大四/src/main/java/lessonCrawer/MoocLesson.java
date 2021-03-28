package lessonCrawer;

import java.util.List;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/3/17 14:03
 * @Description:
 */
public class MoocLesson {
    private String lessonTitle;
    private String authorName;
    private String headImageUrl;
    private String lessonImage;
    private String introduce;
    private String authorJob;
    private List<MoocCapture> captures;

    @Override
    public String toString() {
        return "MoocLesson{" +
                "lessonTitle='" + lessonTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", headImageUrl='" + headImageUrl + '\'' +
                ", lessonImage='" + lessonImage + '\'' +
                ", introduce='" + introduce + '\'' +
                ", authorJob='" + authorJob + '\'' +
                ", captures=" + captures +
                '}';
    }

    public String getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(String lessonImage) {
        this.lessonImage = "https:" + lessonImage;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getAuthorJob() {
        return authorJob;
    }

    public void setAuthorJob(String authorJob) {
        this.authorJob = authorJob;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = "https:" + headImageUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<MoocCapture> getCaptures() {
        return captures;
    }

    public void setCaptures(List<MoocCapture> captures) {
        this.captures = captures;
    }
}
