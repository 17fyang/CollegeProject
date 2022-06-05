package com.stu.yf.fix;

/**
 * 速度控制器
 */
public class SpeedController {
    private long startTime;

    public SpeedController(long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取当前的窗口上移速度，单位：像素/帧
     *
     * @param runtime
     * @return
     */
    public double upSpeed(int runtime) {
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

    /**
     * 获取当前速度
     *
     * @return
     */
    public int speed() {
        int runtime = (int) ((System.currentTimeMillis() - startTime) / 1000);
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

}
