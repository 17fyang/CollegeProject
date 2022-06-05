package com.stu.yf.fix;

import javax.swing.*;

/**
 * 游戏tick线程
 */
public class TickTread extends Thread {
    private final JPanel panel;

    public TickTread(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        long lastTick = System.currentTimeMillis();
        long tickTimes = 0;
        while (true) {
            //TODO 这里的repaint是异步的
            panel.repaint();

            tickTimes++;
            if (tickTimes == 1000) {
                long now = System.currentTimeMillis();
                long fps = (long) (tickTimes / ((now - lastTick) / 1000.0));
                System.out.println("当前的fps是：" + fps);
                lastTick = now;
                tickTimes = 0;
            }
        }
    }

}
