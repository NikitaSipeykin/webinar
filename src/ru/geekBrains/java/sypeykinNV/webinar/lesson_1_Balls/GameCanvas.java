package ru.geekBrains.java.sypeykinNV.webinar.lesson_1_Balls;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    long lastFrameTime;
    MainCircles gameController;

    GameCanvas(MainCircles gameController){
        lastFrameTime = System.nanoTime();
        this.gameController = gameController;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        gameController.onDrawFrame(this, g, deltaTime);

        try {
            Thread.sleep(16);//Добавляет паузу между вызывами метода, это позволяет добиться смены кадров в 60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();//Постоянно вызывает метод painComponent, тем самым вводя метод в бесконечный цикл. Тем самым
        // производя анимацию объектов.
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return  0; }
    public int getBottom() { return getHeight() - 1; }
}
