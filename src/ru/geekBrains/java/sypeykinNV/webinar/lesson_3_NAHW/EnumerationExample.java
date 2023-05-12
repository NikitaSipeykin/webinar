package ru.geekBrains.java.sypeykinNV.webinar.lesson_3_NAHW;

public class EnumerationExample {//перечисление
    enum Colors{
        RED("#FF0000"),
        BLUE("#0000FF"),
        GREEN("#00FF00"),
        BLACK("#000000"),
        WHITE("#FFFFFF");

        private String code;

        Colors(String code){
            this.code = code;
        }

        public String getCode(){
            return  code;
        }
    }

    static class Cat{
        Colors color;
    }

    public static void main(String[] args) {
        Cat c = new Cat();
        c.color = Colors.RED;

        Colors[] colors = Colors.values();
        for (Colors color : colors) {
            System.out.println(color);
        }
    }
}
