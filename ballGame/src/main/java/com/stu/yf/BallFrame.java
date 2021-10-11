package com.stu.yf;


import javax.swing.*;

public class BallFrame extends JFrame {
    private long startTime;

    BallFrame() {
        startTime = System.currentTimeMillis();
        this.setTitle("BALL GAME");
        this.setBounds(750, 0, 600, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }

    public void init() {
        BallPanel ballPanel = new BallPanel(startTime);
        BallFrameKeyListen keyListen = new BallFrameKeyListen();
        keyListen.setBallPanel(ballPanel);
        this.addKeyListener(keyListen);
        this.add(ballPanel);
    }
}
