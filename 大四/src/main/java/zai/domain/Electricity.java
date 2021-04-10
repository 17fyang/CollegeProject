package zai.domain;

import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Objects;

/**
 * @author: 乌鸦坐飞机亠
 * @date: 2021/4/9 17:04
 * @Description:
 */
public class Electricity {
    @ExcelProperty(value = "时间")
    private String time;
    @ExcelProperty(value = "光照强度")
    private String light;
    @ExcelProperty(value = "功率")
    private String power;
    @ExcelProperty(value = "温度")
    private String temperature;
    @ExcelProperty(value = "所处分段")
    private int level;
    @ExcelProperty(value = "段内结果")
    private String levelValue;


    @Override
    public boolean equals(Object o) {
        Electricity that = (Electricity) o;
        return level == that.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(String levelValue) {
        this.levelValue = levelValue;
    }
}
