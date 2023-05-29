package ru.geekBrains.java.sypeykinNV.webinar.lesson_3_Collection.HomeWork;

import java.util.*;

public class HomeWork {
    /*
 1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
    String[] words = {"apple", "banana", "orange", "pear", "kiwi", "peach", "plum", "apple", "banana", "grape",
    "watermelon", "orange", "pear", "strawberry", "cherry", "mango", "pineapple", "apple", "banana", "orange"};
    Найти список слов, из которых состоит массив (дубликаты не считать);
    Посчитать сколько раз встречается каждое слово (использовать HashMap);
2.  Написать простой класс PhoneBook(внутри использовать HasMap);
    Вкачестве ключа использовать фамилию
    В каждой записи всего два поля : phone, e-mail
    Отдельным методом для поиска номера телефона по фамилии ( ввели фамилию, получили ArrayList телефонов),
      и отдельный метод для поиска e-mail по фамилии
     */

    public static void main(String[] args) {
//        taskTwo();
        taskOne();
    }

    private static void taskTwo() {
        PhoneBook phoneBook = new PhoneBook();
        String[][] phoneNumberExamples = {
                {"Ivanov", "+7(123)456-78-90", "ivanov@mail.com"},
                {"Petrov", "+7(234)567-89-01", "petrov@mail.com"},
                {"Sidorov", "+7(345)678-90-12", "sidorov@mail.com"},
                {"Ivanov", "+7 (999) 123-45-67", "ivanov@example.com"},
                {"Petrov", "+7 (999) 234-56-78", "petrov@example.com"},
                {"Ivanov", "+7 (999) 345-67-89", "ivanov2@example.com"},
                {"Ivanov", "+7 (123) 456-78-90", "ivanov@mail.com"},
                {"Petrov", "+7 (234) 567-89-01", "petrov@mail.com"},
                {"Sidorov", "+7 (345) 678-90-12", "sidorov@mail.com"},
                {"Kozlov", "+7 (456) 789-01-23", "kozlov@mail.com"},
                {"Ivanov", "+7 (567) 890-12-34", "ivanov2@mail.com"},
                {"Petrov", "+7 (678) 901-23-45", "petrov2@mail.com"},
                {"Sidorov", "+7 (789) 012-34-56", "sidorov2@mail.com"},
                {"Kozlov", "+7 (890) 123-45-67", "kozlov2@mail.com"}};

        for (int i = 0; i < phoneNumberExamples.length; i++) {
            phoneBook.addContact(phoneNumberExamples[i][0],phoneNumberExamples[i][1],phoneNumberExamples[i][2]);
        }

        List<String> arrayList = phoneBook.getPhonesByLastName("Ivanov");
        List<String> arrayList1 = phoneBook.getEmailByLastName("Ivanov");
        System.out.println(arrayList);
        System.out.println(arrayList1);
    }

    private static void taskOne() {
        String[] words = {"apple", "banana", "orange", "pear", "kiwi", "peach", "plum", "apple", "banana", "grape",
                "watermelon", "orange", "pear", "strawberry", "cherry", "mango", "pineapple", "apple", "banana", "orange"};

        findAndPrintUniqueWordsInArray(words);
        hashMapCounterOccurs(words);

        System.out.println("Ivan's solution");

        System.out.println(getWords(words));

    }

    private static HashSet<String> getWords(String[] array){
        return new HashSet<>(Arrays.asList(array));
    }

    private static void hashMapCounterOccurs(String[] array){
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String word : array) {
            if (hashMap.containsKey(word)){
                hashMap.put(word, hashMap.get(word) + 1);
            }else {
                hashMap.put(word, 1);
            }
        }

        for (String word: hashMap.keySet()) {
            int count = hashMap.get(word);
            System.out.println(word + ": " + count);
        }
    }

    private static void findAndPrintUniqueWordsInArray(String[] array){
        System.out.println(arrayConverter(array));
    }

    private static HashSet arrayConverter(String[] array){
        HashSet<String> stringSet = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            stringSet.add(array[i]);
        }
        return stringSet;
    }
}
