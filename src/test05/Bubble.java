package test05;

import javax.swing.*;

public class Bubble extends JLabel implements Moveable {

    private int x;
    private int y;

    private ImageIcon bubbleIcon;
    private Player player;

    //버블 이동 상태 플래그
    private static final int HORIZONTAL_DISTANCE = 400; // 수평 이동 거리
    private static final int BUBBLE_SPEED_MS = 1;       // 이동 간격 (ms)
    private static final int SCREEN_TOP = 0;            // 화면 상단 경계

    private boolean leftMovig = false;
    private boolean rightMovig = false;
    private boolean upMovig = false;
    // getter
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    // setter
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    // DI
    public Bubble(Player player) {
        this.player = player;
        initData();
        setInitLayout();
        bubbleStartThread();
    }

    // 물방울 이동 Thread 시작
    public void bubbleStartThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(player.getPlayerWay() == PlayerWay.LEFT) {
                    left(); // 왼쪽으로 400px 이동 --> 완료 후 --> up() 호출
                } else {
                    right(); // 오른쪽으로 400px 이동 --> 완료 후 --> up() 호출
                }
            }
        }).start();
    }

    private void initData() {
        bubbleIcon = new ImageIcon("img/bubble.png");
    }

    private void setInitLayout() {
        x = player.getX();
        y = player.getY();
        setIcon(bubbleIcon);
        setSize(50, 50);
        setLocation(x, y);
        setVisible(true);
    }


    @Override
    public void left() {
        leftMovig = true;
        for (int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            x--;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        leftMovig = false;
        up(); //수평이동

    }

    @Override
    public void right() {
        rightMovig = true;
        for (int i = 0; i < HORIZONTAL_DISTANCE; i++) {
            x++;
            setLocation(x, y);
            try {
                Thread.sleep(BUBBLE_SPEED_MS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        rightMovig = false;
        up(); //수평이동 완료 후 상승시작
    }

    @Override
    public void up() {
        upMovig = true;
            while (y > SCREEN_TOP) { // y좌표 맨위가 0이라서
                y--;
                setLocation(x, y);
                try {
                    Thread.sleep(BUBBLE_SPEED_MS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            upMovig = false;
        }
    }

