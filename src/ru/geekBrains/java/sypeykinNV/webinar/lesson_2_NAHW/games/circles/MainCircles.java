package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.circles;

import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.GameCanvas;
import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.GameCanvasListener;
import ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.games.common.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame implements GameCanvasListener {

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    GameObject[] gameObjects = new GameObject[1];
    private int gameObjectsCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        GameCanvas canvas = new GameCanvas(this);
        initApplication();

        //Слушатель мышки
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1)
                    addGameObject(new Ball(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3)
                    removeGameObject();
            }
        });

        add(canvas);
        setVisible(true);
    }

    private void addGameObject(GameObject s){
        if(gameObjectsCount == gameObjects.length){
            GameObject[] temp = new GameObject[gameObjects.length * 2];
            System.arraycopy(gameObjects, 0, temp, 0, gameObjects.length);
            gameObjects = temp;
        }
        gameObjects[gameObjectsCount++] = s;
    }

    private void removeGameObject(){
        if (gameObjectsCount > 1) gameObjectsCount--;
    }

    private void  initApplication(){
       addGameObject(new Background());
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime){
        update(canvas, deltaTime);  //обновление // S = v * t
        render(canvas, g);          //отрисовка
    }

    private void update(GameCanvas canvas, float deltaTime){
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g){
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].render(canvas, g);
        }
    }
}
