package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common;

import java.awt.*;

public interface GameObject {

    void update(GameCanvas canvas, float deltaTime);
    void render(GameCanvas canvas, Graphics g);
}
