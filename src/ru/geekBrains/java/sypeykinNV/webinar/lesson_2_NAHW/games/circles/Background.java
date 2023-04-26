package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.circles;

import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.GameCanvas;
import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.GameObject;


import java.awt.*;

public class Background implements GameObject {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    public void update(GameCanvas gameCanvas, float deltaTime) {//Короче изменение цвета фона используется модель синуса
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
