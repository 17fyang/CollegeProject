package com.stu.yf.fix;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 小球相关类
 */
public class Ball {
    //小球当前位置：x
    private int x;
    //小球当前位置：y
    private int y;
    //小球贴图
    private final Image image;
    //速度控制器
    private final SpeedController speedController;

    public Ball(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.speedController = new SpeedController(System.currentTimeMillis());
    }

    public void tick(BarrierCollection barrierCollection) {
        y++;
    }

    public void down() {
        y++;
    }

    public void drawImage(Graphics g, ImageObserver observer) {
        g.drawImage(image, x, y, observer);
    }
}
