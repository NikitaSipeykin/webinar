package ru.geekBrains.java.sypeykinNV.webinar.lesson_3_NAHW.HomeWork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//public class PhoneBook {
//    HashMap<String, String[]> phoneBook = new HashMap<>();
//
//    public PhoneBook() {
//        phoneBook = new HashMap<>();
//    }
//    public void insertCharacter(String lastName, String phoneNumber, String e_mail){
//        String[] value = new String[2];
//        value[0] = phoneNumber;
//        value[1] = e_mail;
//        phoneBook.put(lastName, value);
//    }
//
//    public ArrayList<String> phoneSearchByLastName(String lastName){
//        ArrayList<String> arrayList = new ArrayList<>();
//
//        for (int i = 0; i < phoneBook.size(); i++) {
//            if(phoneBook.containsKey(lastName)) {
//                String[] arr = phoneBook.get(lastName);
//                arrayList.add(arr[0]);
//            }
//        }
//
//        return arrayList;
//    }
//
//    public ArrayList<String> e_mailSearchByLastName(String lastName){
//        ArrayList<String> arrayList = new ArrayList<>();
//
//
//        return arrayList;
//    }
//}
public class PhoneBook {
    private Map<String, List<Person>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void addContact(String lastName, String phone, String email) {
        Person contactInfo = new Person(phone, email);
        List<Person> contacts = phoneBook.getOrDefault(lastName, new ArrayList<>());
        contacts.add(contactInfo);
        phoneBook.put(lastName, contacts);
    }

    public List<String> getPhonesByLastName(String lastName) {
        List<String> phones = new ArrayList<>();
        List<Person> contacts = phoneBook.get(lastName);
        if (contacts != null) {
            for (Person contact : contacts) {
                phones.add(contact.getPhone());
            }
        }
        return phones;
    }

    public List<String> getEmailByLastName(String lastName) {
        List<String> emails = new ArrayList<>();
        List<Person> contacts = phoneBook.get(lastName);
        if (contacts != null) {
            for (Person contact : contacts) {
                emails.add(contact.getEmail());
            }
        }
        return emails;
    }
}