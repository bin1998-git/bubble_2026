package _my.test03;

import javax.swing.*;

public class Player2 extends JLabel implements Moveable2 {

    private int x;
    private int y;

    private ImageIcon playerR;
    private ImageIcon playerL;

    private final int SPEED = 4;
    private final int JUMP_SPEED = 2;
    private final int JUMP_HEIGHT = 130;

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private boolean leftWallCrash;
    private boolean rightWallCrash;


    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeftWallCrash() {
        return leftWallCrash;
    }


    public boolean isRightWallCrash() {
        return rightWallCrash;
    }


    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    public Player2() {
        initData();
        setInitLayout();
    }

    private void initData() {
        playerR = new ImageIcon("img/playerR.png");
        playerR = new ImageIcon("img/playerL.png");
    }

    private void setInitLayout() {

        x = 55;
        y = 535;
        setSize(50, 50);
        setIcon(playerR);
        setLocation(x, y);
    }

    @Override
    public void left() {
        if (left) {
            return;
        }
        left = true;
        setIcon(playerL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (left) {
                    x = x - SPEED;
                    setLocation(x, y);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

    @Override
    public void right() {
        if (right) {
            return;
        }
        right = true;
        setIcon(playerR);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (right) {
                    x = x + SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

    }


    @Override
    public void up() {
        if (up) {
            return;
        }
        up = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < JUMP_HEIGHT / JUMP_SPEED; i++) {
                    y = y - JUMP_SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                up = false;
                down();
            }
        }).start();

    }

    @Override
    public void down() {
        down = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < JUMP_HEIGHT / JUMP_SPEED; i++) {
                    y = y + JUMP_SPEED;
                    setLocation(x, y);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                down = false;
            }
        }).start();

    }
}
