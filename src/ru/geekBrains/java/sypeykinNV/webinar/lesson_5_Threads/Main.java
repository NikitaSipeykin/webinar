package ru.geekBrains.java.sypeykinNV.webinar.lesson_5_Threads;

public class Main {
    private static class MyThread extends Thread{

        public MyThread(String name) {
            super(name);
            start();
        }

        @Override
        public void run() {
           // System.out.println("Hello from " + getName() + " This is " + Thread.currentThread().getName());
//           while (!isInterrupted()) {
//
//               System.out.println("Thread " + Thread.currentThread().getName() + " is work!");
//           }
            System.out.println("Thread started");
            for (long i = 0; i < 5000000000L; i++) {
                long a = i / 5;
            }
            System.out.println("Thread finished");
        }
    }

    public static void main(String[] args) {


    }

    private static void threadExample2() {
        MyThread mt1 = new MyThread("MT1");
//        for (int i = 0; i < 2000000000; i++) {
//            int a = i / 5;
//        }
//        mt1.interrupt();

        try {
            mt1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("things after thread");
    }

    private static void exampleTread() {
        //        //.run не создает отдельный поток
//        MyThread mt1 = new MyThread("mt1");
//        mt1.run();
//        //.start запускает отдельный попток
//        MyThread mt2 = new MyThread("mt2");
//        mt2.start();
        System.out.println("Hello from " + Thread.currentThread().getName());


        //Second case
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };
        Thread t = new Thread(r);
        t.start();

        MyThread mt1 = new MyThread("mt1");
        for (int i = 0; i < 2000000000; i++) {
            int a = i / 5;
        }
    }
}
