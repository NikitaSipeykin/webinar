package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.circles;

import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.GameCanvas;
import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.Sprite;

import java.awt.*;

public class Ball extends Sprite {

    private final Color color = new Color(
            (int)(Math.random() * 255),
            (int)(Math.random() * 255),
            (int)(Math.random() * 255)
    );

    private float vX = (float)(100f + (Math.random() * 200f));
    private float vY = (float)(100f + (Math.random() * 200f));

    Ball(){
        halfHeight = 20 + (float)(Math.random() * 50f);
        halfWidth = halfHeight;
    }

    Ball(int x, int y){
        this();//вызывает пустой конструктор выше
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
       x += vX * deltaTime;
       y += vY * deltaTime;

        if (getRight() > canvas.getRight()){
           setRight(canvas.getRight());
           vX = -vX;
        }
        if (getLeft() < canvas.getLeft()){
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()){
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()){
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    public void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }
}
