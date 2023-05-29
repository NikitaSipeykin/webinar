package ru.geekBrains.java.sypeykinNV.webinar.lesson_5_Threads.HomeWork;

public class Homework {
    /*
    * 1. Необходимо написать два метода, которые делают следующее:
    *    1) Создают одномерный длинный массив, например:
    *       static final int size = 10000000;
    *       static final int h = size / 2;
    *       float[] arr = new float[size];
    *    2) Заполняют этот массив единицами;
    *    3) Засекают время выполнения : long a = System.currentTimeMillis();
    *    4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    *       arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)  * Math.cos(0.4f + i / 2));
    *    5) Проверяется время окончания метода System.currentTimeMillis();
    *    6) В консоль выводится время работы: System.out.println(System.currentTimMillis() - a);
    * Отличие первого метода от второго:
    * Первый просто бежит по массиву и вычисляет значения.
    * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы
    *  обратно в один.
    *
    * Пример деления одного массива на два:
    *   System.arraycopy(arr, 0, a1, 0, h);
    *   System.arraycopy(arr, h, a2, 0, h);
    *
    * Пример обратной склейки:
    *   System.arraycopy(a1, 0, arr, 0, h);
    *   System.arraycopy(a2, 0, arr, h, h);
    *
    * Примечание:
    * System.arraycopy() - копирует данные из одного массива в другой:
    * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда
    *   начинаем записывать данные в массив-назначение, сколько ячеек копируем);
    * По замерам времени:
    * для первого метода надо считать время только на цикл расчета:
    *
    *  for (int i = 0; i < size; i++) {
    *         arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)  * Math.cos(0.4f + i / 2));
    *   }
    *
    * для второго метода замеряем время разбивки на 2, просчета каждого из двух массивов и склеки.
    * */

    static final int size = 10000000;
    static final int h = size / 2;

    long a = System.currentTimeMillis();

    public static void main(String[] args) {

        tryWithoutThread();
        tryWithThread();
    }

    private static void tryWithoutThread() {

        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)  * Math.cos(0.4f + i / 2));
        }

        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void tryWithThread() {

        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];


        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        MyThread myThread = new MyThread("Mt1", a1);
        MyThread myThread1 = new MyThread("Mt2", a2);

        try {
            myThread1.join();
            myThread.join();

            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);

            System.currentTimeMillis();
            System.out.println(System.currentTimeMillis() - a);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private static class MyThread extends Thread{
        float[] mainArray;

        public MyThread(String name, float[] mainArray) {
            super(name);
            this.mainArray = mainArray;
            start();

        }

        @Override
        public void run() {
            System.out.println("Thread " + super.getName() + " is begin to run" );

            for (int i = 0; i < mainArray.length; i++) {
                mainArray[i] = (float)(mainArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)  * Math.cos(0.4f + i / 2));
            }

        }
    }
}
