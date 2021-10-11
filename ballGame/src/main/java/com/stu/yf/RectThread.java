package com.stu.yf;

import java.awt.*;


public class RectThread extends Thread {
    private Graphics g;
    private BallPanel ballPanel;
    private double y = 680;
    private boolean on = true;
    private boolean first = true;

    RectThread() {
    }

    RectThread(Graphics g, BallPanel p) {
        this.g = g;
        this.ballPanel = p;
    }

    public void run() {
        int x = (int) (Math.random() * 360);
        int width = (int) (Math.random() * 120 + 80);
        Image rect[] = new Image[9];
        rect[0] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[1] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[2] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[3] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[4] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[5] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[6] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[7] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        rect[8] = Toolkit.getDefaultToolkit().getImage(PathUtils.get("line.png").getAbsolutePath());
        Image rectAll = null;
        int num = (int) (Math.random() * 8);
        for (int i = 0; i < 9; i++) {
            if (i == num) {
                rectAll = rect[i];
                rect[i] = null;
            } else {
                rect[i] = null;
            }
        }
        while (on == true) {
            y -= upSpeed(ballPanel.time(ballPanel.getStartTime()) / 1000);
            //y-=0.6;
            this.g = ballPanel.getG();
            g.drawImage(rectAll, x, (int) y, width, 25, ballPanel);
            dataArray.getDataArray().enterData(x, (int) y, width);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (first == false) {
                on = false;
            }
            if (y <= -50 || ballPanel.isGame() == false) {
                first = false;
            }
        }

    }

    private double upSpeed(int runtime) {
        double result = 0;
        if (runtime <= 10)
            result = 0.5;
        else if (runtime <= 20)
            result = 0.7;
        else if (runtime <= 30)
            result = 0.85;
        else if (runtime <= 50)
            result = 1.07;
        else if (runtime <= 65)
            result = 1.32;
        else if (runtime <= 85)
            result = 1.50;
        else if (runtime <= 110)
            result = 1.72;
        else if (runtime <= 130)
            result = 2;
        else if (runtime <= 150)
            result = 2.34;
        else
            result = 2.60;
        return result;
    }
}
