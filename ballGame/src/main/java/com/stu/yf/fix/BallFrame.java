package com.stu.yf.fix;


import javax.swing.*;

public class BallFrame extends JFrame {
    public static final int START_X = 750;
    public static final int START_Y = 0;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;

    BallFrame() {
        this.setTitle("BALL GAME");
        this.setBounds(START_X, START_Y, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        init();
        this.setVisible(true);
    }

    public void init() {
        BallPanel ballPanel = new BallPanel();
        this.add(ballPanel);
    }

    public static void main(String[] args) {
        new BallFrame();
    }
}
