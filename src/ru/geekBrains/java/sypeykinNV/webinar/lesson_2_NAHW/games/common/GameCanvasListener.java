package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common;

import java.awt.*;

public interface GameCanvasListener {
    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime);
}
