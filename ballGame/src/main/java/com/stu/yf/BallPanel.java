package com.stu.yf;


import javax.swing.*;
import java.awt.*;


public class BallPanel extends JPanel {
    int x;
    private int ballX = 350;
    private double ballY = 0;
    private Graphics g;
    private boolean leftMove;
    private boolean rightMove;
    private long startTime;
    private boolean newRect = true;
    private boolean game = true;


    BallPanel() {
    }

    BallPanel(long time) {
        this.startTime = time;
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.setG(g);
        run();
        if (ballDown() == true) {
            ballY += 2;
        } else {
            double up = upSpeed(time(startTime) / 1000);
            ballY -= up * 1.6;
        }
        Image ball = Toolkit.getDefaultToolkit().getImage(PathUtils.get("ball.png").getAbsolutePath());
        g.drawImage(ball, ballX, (int) ballY, 30, 30, this);
        int speed = speed(startTime);

        if (time(startTime) % speed > 0 && time(startTime) % speed < (speed / 2) && newRect == true) {
            newRect = false;
            RectThread rectThread = new RectThread(g, this);
            rectThread.start();
        } else if (time(startTime) % speed > (speed / 2) && newRect == false) {
            newRect = true;
        }


        if (ballY >= -10 && ballY <= 670) {
            this.repaint();
        } else {
            System.out.println("GAME OVER!");
            game = false;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int speed(long startTime) {
        int runtime = time(startTime) / 1000;
        int result = 0;
        if (runtime <= 10)
            result = 1300;
        else if (runtime <= 20)
            result = 1060;
        else if (runtime <= 30)
            result = 1000;
        else if (runtime <= 50)
            result = 800;
        else if (runtime <= 65)
            result = 670;
        else if (runtime <= 85)
            result = 590;
        else if (runtime <= 110)
            result = 510;
        else if (runtime <= 130)
            result = 450;
        else if (runtime <= 150)
            result = 360;
        else
            result = 300;
        return result;
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

    public int time(long time) {
        int t = (int) (System.currentTimeMillis() - time);
        return t;
    }

    public boolean ballDown() {
        boolean result = false;
        result = dataArray.getDataArray().touch(ballX + 15, (int) ballY + 20);
        return !result;
    }

    public void run() {
        if (leftMove == true && ballX >= 0) {
            //if(!dataArray.getDataArray().leftBoardTouch(ballX+30, (int)ballY+15))
            {

                ballX -= 2;
            }
        }
        if (rightMove == true && ballX <= 555) {
            //if(!dataArray.getDataArray().rightBoardTouch(ballX, (int)ballY+15))
            {
                ballX += 2;
            }

        }
    }

    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public boolean isLeftMove() {
        return leftMove;
    }

    public void setLeftMove(boolean leftMove) {
        this.leftMove = leftMove;
    }

    public boolean isRightMove() {
        return rightMove;
    }

    public void setRightMove(boolean rightMove) {
        this.rightMove = rightMove;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

}
