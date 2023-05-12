package ru.geekBrains.java.sypeykinNV.webinar.lesson_2_NAHW.HomeWork;

public class Main {

    /*
    1. Есть строка вида: "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0"; (другими словами матрица 4х4)
    2 3 2 2
    5 6 7 1
    300 3 1 0
    Написать метод, на вход которого подается такая строка, метод должен преобразовать строку в двумерный массив типа
    String[][];

    2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и
     вернуть результат;

    3. Ваши методы должны бросить исключения в случаях:
        Если размер матрицы, полученной из строки, не равен 4х4;
        Если в одной из ячеек полученной матрицы не число; (например символ или слово)

    4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.

    TODO 5. * Написать собственные классы исключений для каждого из случаев
    */

    private static final String STR_00 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    private static final String STR_01 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0\n10 3 1 2";
    private static final String STR_02 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0\nten three one two";
    private static final String STR_03 = "10 3 1 2\n2 3 2 2\n5 6 7 1\nten three one two";
    private static final String STR_04 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n!) # ! @";
    private static final String STR_05 = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0 7";

    private static final String[] AllStrings = {STR_00, STR_01, STR_02, STR_03, STR_04, STR_05};

    public static void main(String[] args) {
        for (int i = 0; i < AllStrings.length; i++) {

            try{
                ArrayDrawer.arrayDrawer(stringToArrayConverter(AllStrings[i]));
                System.out.println();
                ArrayDrawer.arrayDrawer(stringArrToIntArr(stringToArrayConverter(AllStrings[i])));
                System.out.println("\nResult of mathematics operations by " + i  + " array is " +
                        sumAndDivide(stringArrToIntArr(stringToArrayConverter(AllStrings[i]))) + "\n");
            }catch (Exception e){
                System.err.println("Found Exception in array STR_0" + i);
                e.printStackTrace();
            }
        }

    }

    private static String[][] stringToArrayConverter(String s) throws Exception{  // "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0" (на входе)
        String[] rows = s.split("\n");                      //""10 3 1 2","2 3 2 2","5 6 7 1","300 3 1 0"" (дробит по символу \n)
        if (rows.length > 4) throw new Exception();

        String[][] arr = new String[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            String[] values = rows[i].split(" ");           //""10","3","1","2"" (дробит по пробелу)
            if (values.length > 4) throw new Exception();

            arr[i] = new String[values.length];

            for (int j = 0; j < values.length; j++) {
                try {
                    arr[i][j] = values[j];                        //вносит значение в масив arr
                    Integer.parseInt(arr[i][j]);

                }catch (NumberFormatException numberFormatException){
                    throw new NumberFormatException();
                }
            }
        }
        return arr;
    }

    private static int[][] stringArrToIntArr(String[][] strings){
        int[][] arr = new int[strings.length][strings[0].length];
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                arr[i][j] = Integer.parseInt(strings[i][j]);
            }
        }
        return arr;
    }

    private static int sumAndDivide (int[][] arr){
        int result = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                result = result + arr[i][j];
            }
        }

        result = result / 2;
        return result;
    }
}
