package ru.razhapov.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Users implements UserMng{
    private String name;
    private String number;
    private static long userId = 0;
    private long id = 0;
    public static List<Users> usersList = new ArrayList<Users>();
    public List<Contacts> userPhoneBook = new ArrayList<Contacts>();

    // конструктор
    public Users(String name, String number) {
        try {
            if (!number.isEmpty() && Long.parseLong(number) > 0 && checkNumberRegex(number)) {
                this.id = ++userId;
                this.name = name;
                this.number = number;
                usersList.add(this);
                System.out.println("Пользователь " + name + " добавлен!");
            }
        } catch(Exception e){
            System.out.println("Ошибка, пользователь не добавлен.");
        }
    }

//    // проверяем строку на наличие цифр
//    public static boolean checkString(String string) {
//        try {
//            Long.parseLong(string);
//        } catch (Exception e) {
//            return false;// false при строковом аргументе
//        }
//        return true;// true при числах
//    }

    // проверяем строку на наличие цифр
    public static boolean checkStringRegex(String string) {
        if (string.matches("(?!^[0-9]*$)(?!^[a-zA-ZА-Яа-я]*$)^([a-zA-Z0-9А-Яа-я]{2,15})$")){
            return true;
        }
        return false;
    }

    // проверяем строку на наличие цифр
    public static boolean checkNumberRegex(String string) {
        if (string.matches("\\d{1,15}")){
            return true;
        }
        return false;
    }


    // добавление пользователя
    public static void addUser(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = null;
        String number = null;

        System.out.println("Введите имя добавляемого пользователя: ");
        try {
            name = reader.readLine();
        } catch (IOException e) {
            System.out.println("Пользователь не добавлен: Некорректно введено имя: " + name);
        }
        if (name.isEmpty()) {
            System.out.println("Пользователь не добавлен: Нет имени.");
        }
        else if (checkNumberRegex(name)) {
            System.out.println("Пользователь не добавлен: В строке присутствуют числа " + name);
        }
        else if (!name.isEmpty() && !checkNumberRegex(name)){
            System.out.println("Введите номер добавляемого пользователя: ");
            try {
                number = reader.readLine();
            } catch (IOException e) {
                System.out.println("Пользователь не добавлен: Нельзя задавать такой номер: " + number);
            }
            if (number.isEmpty()) {
                System.out.println("Пользователь не добавлен: Нет номера");
            } else if (checkStringRegex(number)) {
                System.out.println("Пользователь не добавлен: В номере не должно быть букв: " + number);
            } else if (checkNumberRegex(number)) {
                new Users(name, number);
            }
            else {
                System.out.println("Пользователь не добавлен: Некорректно введен номер: " + number);
            }
        }
        else {
            System.out.println("Пользователь не добавлен: Некорректно введено имя: " + name);
        }

    }

    // редактируем пользователя
    public static void editUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String quest1 = null;
        String quest2 = null;
        String name = null;
        String number = null;
        int choise = 0;
        System.out.println("Введите id пользователя для изменения: ");
        try {
            choise = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Ошибка! Вводятся только цифры!");
        }
        System.out.println("Редактируем пользователя ");
        for (int i = 0; i < usersList.size(); i++) { // цикл по списку пользователей
            if(usersList.get(i).getId() == (choise)){
                System.out.println("Редактируем имя?");
                try {
                    quest1 = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Некорректный символ: " + quest1);
                }
                if(quest1.equals("lf") || quest1.equals("да") || quest1.equals("yes")){
                    System.out.println("Введите имя:");
                    try {
                        name = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Некорректно введено имя: " + name);
                    }
                    if(name.isEmpty()){
                        System.out.println("Пользователь не отредактирован: Нет имени");
                    }
                    else if(checkNumberRegex(name)){
                        System.out.println("Пользователь не отредактирован: Некорректно введено имя: " + name);
                    }
                    else if(checkStringRegex(name)){
                        usersList.get(i).setName(name);
                    }
                }
                System.out.println("Редактируем номер?");
                try {
                    quest2 = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Некорректно введен номер: " + number);
                }
                if(quest2.equals("lf") || quest2.equals("да") || quest2.equals("yes")){
                    System.out.println("Введите номер:");
                    try {
                        number = reader.readLine();
                    } catch (IOException e) {
                        System.out.println("Некорректно введен номер: " + number);
                    }
                    if(number.isEmpty()){
                        System.out.println("Пользователь не отредактирован: Нет номера");
                    }
                    else if(!checkNumberRegex(number)){
                        System.out.println("Пользователь не отредактирован: Некорректно введен номер: " + number);
                    }
                    else if(checkNumberRegex(number)){
                        usersList.get(i).setNumber(number);
                    }
                }
            }
        }
        if(!(quest1.equals("lf") || quest1.equals("да") || quest1.equals("yes")) && !(quest2.equals("lf") || quest2.equals("да") || quest2.equals("yes"))){
            System.out.println("Пользователь не изменён");
        }
        else{
            System.out.println("Пользователь изменён.");
        }

    }

    // показать всех пользователей
    public static void showAllUsers() {
        System.out.println("Список всех пользователей: ");
        for (Users user : Users.usersList) {
            System.out.println(user);
        }
        System.out.println("");
    }

    // удаление пользователя
    public static void removeUser(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choise = 0;
        int count = 0;
        System.out.println("Введите id пользователя для удаления: ");
        try {
            choise = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("Ошибка! Вводятся только цифры!");
        }
        for (int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).getId() == choise){
                System.out.println("Удаляем телефонную книгу...");
                for(int j = usersList.get(i).userPhoneBook.size()-1; j >= 0;){
                    usersList.get(i).userPhoneBook.remove(j);
                    j--;
                    count++;
                }
                System.out.println("Удалено " + count+ " контактов. Удаляем пользователя");
                usersList.remove(i);
                System.out.println("Пользователь удалён");
                break;
            }
        }
        System.out.println("");
    }

    // поиск пользователя
    public static void findUser(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String search = null;
        int count = 0;
        List<Users> list = new ArrayList<Users>();

        System.out.print("Поиск: ");
        try {
            search = reader.readLine();
        } catch (IOException e) {
            System.out.println("Строка некорректна: " + search);
        }
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getName().contains(search)) {
                list.add(usersList.get(i));
                count++;
            }
            System.out.println(usersList.get(i).getName());
        }
        if(count > 0){
            System.out.println("По запросу " + search + " найдено " + count + " объектов: ");
            for (Users elements : list) {
                System.out.println(elements);
            }
        }
        else System.out.println("По запросу " + search + " ничего не найдено!");


    }

    // получение пользователя по id
    public static void userGetId(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String id = null;

        System.out.println("Введите id: ");
        try {
            id = reader.readLine();
        } catch (IOException e) {
            System.out.println("Строка имеет некорректный символ: " + id);
        }
        if (checkNumberRegex(id)){
            int intId = Integer.parseInt(id);

            for (int i = 0; i < usersList.size(); i++) {
                if(intId == usersList.get(i).getId()){
                    System.out.println("Пользователь с id " + intId + ":\n" + usersList.get(i));
                    break;
                }
                else{
                }
            }
        }
        else{
            System.out.println("Необходимо ввести цифры, а не то что вы только что напечатали: " + id);
        }
    }

    // добавление контакта в телефонную книгу пользователя
    public void addContactList(){
        System.out.println("Добавляем контакт " + name + " в телефонную книгу пользователя " + this.getName() + "\n");
        userPhoneBook.add(new Contacts(name, number));
    }

    // получение контакта по id
    public Contacts contactsGetId(int id){
        Contacts contacts = null;
        System.out.println("Получаем контакт по id: "+ id +" у пользователя " + this.getName());
        for (int i = 0; i < userPhoneBook.size(); i++) {
            if(usersList.get(i).getId() == id){
                contacts = userPhoneBook.get(i);
                break;
            }
            else{
            }
        }
        return contacts;
    }

    //поиск контакта в телефонной книге пользователя номеру телефона
    public void findContact(String number){
        List<Contacts> list = new ArrayList<Contacts>();
        int count = 0;
        System.out.println("Поиск контакта с номером телефона: " + number + " в телефонной книге пользователя " + this.getName());
        for (int i = 0; i < this.userPhoneBook.size(); i++) {
            if(this.userPhoneBook.get(i).getNumber().equals(number)){
                list.add(userPhoneBook.get(i));
                count++;
            }
            else{
            }
        }
        if(count > 0) {
            System.out.println("Результатов поиска: " + count + "\n" + list);
        }
        else{
            System.out.println("Нет такого контакта с таким номером: " + number + " в телефонной книге пользователя " + this.getName());
        }
        for (int i = list.size()-1; i >= 0; i--) {
            list.remove(i);
        }
        count = 0;
        System.out.println("");
    }

    // удаление контакта в телефонной книге
    public void removeContact(String name){
        int count = 0;
        System.out.println("Удаление контакта " + name + " пользователя " + this.getName());
        if(!name.isEmpty() && checkStringRegex(name)){
            for (int i = 0; i < this.userPhoneBook.size(); i++) {
                if(name.equals(this.userPhoneBook.get(i).getName())){
                    this.userPhoneBook.remove(i);
                    System.out.println("Контакт " + name +" удалён \n");
                    count++;
                    break;
                }
            }
            if(count == 0){
                System.out.println("Контакт "+ name +" не найден\n");
            }
        }
        else if(name.isEmpty()){
            System.out.println("Пустое значение\n");
        }
        else if (checkNumberRegex(name)){
            System.out.println("Вы ввели цифры\n");
        }
    }

    // показать все контакты пользователя
    public void showAllContactUserPhoneBook(){
        System.out.println("Список всех контактов, пользователя " +this.getName()+ ": ");
        for(int i = 0; i < this.userPhoneBook.size()-1; i++){
            System.out.println(userPhoneBook.toString());
        }

        System.out.println("");
    }

    // редактирование контакта в телефонной книге пользователя
    public void editContactInUserPhoneBook(String oldname, String newname, String number){
        try {
            if(checkStringRegex(newname) && checkNumberRegex(number)){ // если с аргументами все хорошо(имя это не цифры, а номер телефона это не буквы)
                System.out.println("Редактируем контакт " + oldname + " пользователя " + this.getName());
                for (int i = 0; i < this.userPhoneBook.size(); i++) { // цикл по списку пользователей
                    if (!newname.isEmpty() && !number.isEmpty()){ // если имя и номер не пустые
                        if (userPhoneBook.get(i).getName().equals(oldname)) {
                            System.out.println("Редактируем имя и номер");
                            userPhoneBook.get(i).setName(newname); // изменяем имя у контакта
                            userPhoneBook.get(i).setNumber(number); // меняем его номер
                        }
                    }
                    else if(number.isEmpty()) { // если пустой только номер изменяем имя
                        System.out.println("Редактируем имя");
                        if (userPhoneBook.get(i).getName().equals(oldname)) {
                            userPhoneBook.get(i).setName(newname);
                        }
                    }
                    else if (newname.isEmpty()){ // если пустое имя, изменяем только номер телефона
                        System.out.println("Редактируем номер");
                        if (userPhoneBook.get(i).getName().equals(oldname)) {
                            userPhoneBook.get(i).setNumber(number);
                        }
                    }
                }
            }
            else if(checkStringRegex(newname)){ // если если имя введено верно, но номер с ошибкой
                System.out.println("Некорректно введен номер: " + number);
            }
            else { // если есть ошибка в имени то выводим соответсвующее сообщение
                System.out.println("Некорректно введено имя: " + newname);
            }
        } catch (Exception e){ // выбрасывает эксипшн при неправильном вводе номера телефона
            System.out.println("Некорректно введен номер: " + number);
        }
        System.out.println("");
    }


    @Override
    public String toString() {
        return "Имя: " + this.name
                + " Номер: " + this.number
                + " id: " + this.id
                + " Телефонный справочник: " + userPhoneBook;
    }


    // setters and getters

    public long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}
