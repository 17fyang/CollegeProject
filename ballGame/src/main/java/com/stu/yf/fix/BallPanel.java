package com.stu.yf.fix;


import com.stu.yf.PathUtils;

import javax.swing.*;
import java.awt.*;


public class BallPanel extends JPanel {
    //小球
    private final Ball ball;

    //障碍物集合
    private BarrierCollection barrierCollection;

    BallPanel() {
        //初始化小球对象
        Image ballImage = Toolkit.getDefaultToolkit().getImage(PathUtils.get("ball.png").getAbsolutePath());
        this.ball = new Ball(BallFrame.WIDTH / 2, 0, ballImage);

        //初始化障碍物集合
        this.barrierCollection = new BarrierCollection();

        //创建tick线程
        new TickTread(this).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //障碍物集合先执行tick
        this.barrierCollection.tick();

        //小球执行tick
        this.ball.tick(this.barrierCollection);

        this.barrierCollection.drawImage(g, this);
        this.ball.drawImage(g, this);
    }

}
