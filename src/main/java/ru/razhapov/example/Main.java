package ru.razhapov.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        boolean isTrue = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choise = 0;
        while (isTrue){
            System.out.println(
                    "1. add user\n" +
                    "2. edit user\n" +
                    "3. remove user\n" +
                    "4. find user\n" +
                    "5. get id user\n" +
                    "6. show all users\n" +
                    "7. exit");
            try {
                choise = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Ошибка! Вводятся только цифры!");
            }
            switch (choise){
                case 1:
                    Users.addUser();
                    break;
                case 2:
                    Users.usersList.get(1).editUser();
                    break;
                case 3:
                    Users.removeUser();
                    break;
                case 4:
                    Users.findUser();
                    break;
                case 5:
                    Users.userGetId();
                    break;
                case 6:
                    Users.showAllUsers();
                    break;
                case 7:
                    isTrue = false;
                    break;
                default:
                    System.out.println("Нет такого пункта.");
                    break;
            }
        }


//        Users.addUser();
//        Users.addUser();
//        Users.findUser();
//        Users.userGetId();
//        Users.usersList.get(1).editUser();

//        Thread.sleep(1000);
//        Users.showAllUsers();

//        Users.usersList.get(0).addContactList("valera", "789676756");
//        Users.usersList.get(0).addContactList("misha", "32792");
//        Users.usersList.get(1).addContactList("sveta", "37252385");
//        Users.usersList.get(1).addContactList("nikita", "9128");
//        Users.usersList.get(2).addContactList("larisa", "07807");
//        Users.usersList.get(2).addContactList("alina", "123532");
//
//        user.addUser("ivan", "4325423432");
//        user.addUser("", "-2");
//        user.addUser("532552", "bfwljefkew");
//        user.addUser("frodo", "bfwljefkew");
//        user.removeUser("oleg");
//
//        Users.usersList.get(0).findContact("32792");
//        user.findUser("sa");
//        System.out.println("==============");
//        user.findUser("sant");
//        System.out.println("==============");
//        user.findUser("santa");
//        System.out.println("==============");
//
//        Users.usersList.get(1).editUser("feofan", "123");
//        System.out.println(Users.userGetId(3));
//        System.out.println(Users.usersList.get(1).contactsGetId(1));
//        System.out.println(Users.usersList.get(0));
//        user.showAllUsers();
//        Users.usersList.get(1).removeContact("");
//        Users.usersList.get(1).removeContact("32423");
//        Users.usersList.get(1).removeContact("gfdgfd");
//        Users.usersList.get(1).removeContact("alina");
//        user.showAllUsers();
//        Users.usersList.get(0).showAllContactUserPhoneBook();
//        Users.usersList.get(0).editContactInUserPhoneBook("misha","2312", "65464325");
//        Users.usersList.get(0).editContactInUserPhoneBook("misha","nastya", "dfsdf");
//        Users.usersList.get(0).editContactInUserPhoneBook("misha","342423", "dff3sdf");
//        Users.usersList.get(0).editContactInUserPhoneBook("misha","nastya", "1111111");
//        Users.usersList.get(0).showAllContactUserPhoneBook();

    }
}