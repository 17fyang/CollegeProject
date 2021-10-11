package com.stu.yf;


public class dataArray {
    private int dataY[] = new int[10];
    private int dataX[] = new int[10];
    private int rectLong[] = new int[10];
    private static dataArray array = new dataArray();

    public static dataArray getDataArray() {
        return array;
    }

    dataArray() {
        for (int i = 0; i < 10; i++) {
            dataY[i] = -50;
            rectLong[i] = 200;
        }
    }

    public boolean touch(int x, int y) {
        boolean result = false;
        for (int i = 0; i < 10; i++) {
            if (dataY[i] - y <= 10 && dataY[i] - y >= -20) {
                if (x >= dataX[i] && x <= dataX[i] + rectLong[i]) {
                    result = true;
                }
            }
        }
        return result;
    }

    /*public boolean leftBoardTouch(int x,int y)
    {
        boolean result=false;
        for(int i=0;i<10;i++)
        {
            if(dataX[i]-x<=0 && dataX[i]-x>=-5 && dataY[i]<=y && (25+dataY[i])>=y)
            {
                result=true;
            }
        }
        return result;
    }
    public boolean rightBoardTouch(int x,int y)
    {
        boolean result=false;
        for(int i=0;i<10;i++)
        {
            if(dataX[i]-x>=0 && dataX[i]-x<=5 && dataY[i]<=y && (25+dataY[i])>=y)
            {
                result=true;
            }
        }
        return result;
    }*/
    public void enterData(int x, int y, int width) {
        boolean find = false;
        boolean setup = false;
        for (int i = 0; i < 10; i++) {
            if (Math.abs(dataY[i] - y) <= 8 && Math.abs(dataX[i] - x) <= 8 && setup == false) {
                dataY[i] = y;
                dataX[i] = x;
                rectLong[i] = width;
                find = true;

            }
        }
        if (find == false) {
            for (int i = 0; i < 10; i++) {
                if (dataY[i] <= -30 && setup == false) {
                    dataX[i] = x;
                    dataY[i] = y;
                    setup = true;
                }
            }
        }
    }

    public int[] getDataY() {
        return dataY;
    }

    public void setDataY(int[] dataY) {
        this.dataY = dataY;
    }

    public int[] getRectLong() {
        return rectLong;
    }

    public void setRectLong(int[] rectLong) {
        this.rectLong = rectLong;
    }

    public int[] getDataX() {
        return dataX;
    }

    public void setDataX(int[] dataX) {
        this.dataX = dataX;
    }
}
