package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW;

public class Main {
    public static void main(String[] args) {
        //Anonymous classes

        class MouseListenerImpl implements MouseListener{

            @Override
            public void mouseClicked() {
                System.out.println("Here we clicked");
            }
        }

        //Стандарт
        MouseListenerImpl m = new MouseListenerImpl();
        addMouseListener(m);

        //Безымянный экземпляр
        addMouseListener(new MouseListenerImpl());

        //напрямую используя интерфейс //Ананимный класс
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked() {
                System.out.println("Here we clicked");
            }
        });


        //лямбда выражение () ->
        addMouseListener( () -> {
                System.out.println("Here we clicked");

        });

        addMouseListener( () -> System.out.println("Here we clicked"));

        //закомментированны части от которых мы избавились используя лямбда выражение
        addMouseListener(/*new MouseListener() {
            @Override
            public void mouseClicked*/() -> /*{*/
                System.out.println("Here we clicked")/*;
            }
        }*/);
    }

    public static void addMouseListener(MouseListener l){
        l.mouseClicked();
    }

    public interface MouseListener{
        void mouseClicked();
    }
}
