package com.stu.yf;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallFrameKeyListen implements KeyListener {
    private BallPanel ballPanel;

    public void setBallPanel(BallPanel e) {
        this.ballPanel = e;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'd')
            ballPanel.setRightMove(true);
        if (e.getKeyChar() == 'a')
            ballPanel.setLeftMove(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'd')
            ballPanel.setRightMove(false);
        if (e.getKeyChar() == 'a')
            ballPanel.setLeftMove(false);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }


}
