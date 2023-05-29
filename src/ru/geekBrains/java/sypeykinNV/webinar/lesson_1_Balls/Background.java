package ru.geekBrains.java.sypeykinNV.webinar.lesson_1_Balls;

import java.awt.*;

public class Background extends Sprite{
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    void update(GameCanvas gameCanvas, float deltaTime) {//Короче изменение цвета фона используется модель синуса
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(GameCanvas gameCanvas, Graphics g) {
        gameCanvas.setBackground(color);
    }
}
