package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class UserController {
    UserRepo userRepo = new UserRepo();
    ArrayList<User> usersList = userRepo.loadUsersFromBase();
    Scanner scanner = new Scanner(System.in);

    public void saveUsersAndExit() {
        userRepo.saveUsersToBase(usersList);
    }

    // Зарегистрировать нового пользователя
    public void addNewUser() {
        System.out.println("Введите фамилию: ");
        String surname = notEmptyLettersOnlyInput();
        System.out.println("Введите имя: ");
        String name = notEmptyLettersOnlyInput();
        System.out.println("Введите e-mail:");
        String email;
        do {
            email = scanner.nextLine();
        } while(!validateEmail(email));
        System.out.println("Придумайте пароль: ");
        String password = scanner.nextLine();

        usersList.add(new User(surname, name, email, password, new ArrayList<Integer>()));
    }

    // Авторизовать пользователя
    public User loginUser(String login, String password) {
        for(User user : usersList) {
            if(user.getEmail().equals(login)) {
                if(user.getPassWord().equals(password))
                    return user;
            }
        }
        return null;
    }

    //Удалить пользователя
    public void remove(User user){
        usersList.remove(user);
    }

    // Ввод только букв и строка не пустая
    public static String notEmptyLettersOnlyInput() {
        Scanner scanner = new Scanner(System.in);
        String inpString;

        while((inpString = scanner.nextLine()).isEmpty() | !isAlpha(inpString))
            System.out.println("Это поле не может быть пустым и должно содержать только буквы\n" +
                    "Повторите ввод, пожалуйста: ");
        return inpString;
    }

    public static boolean isAlpha(String s)
    {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    public boolean validateEmail(String email) {
        if(email.indexOf('@') == -1 || email.indexOf('.') == -1) {
            System.out.println("Адрес электронной почты должен содержать символы @ и \".\" Повторите ввод: ");
            return false;
        }
        for(User user : usersList){
            if(user.getEmail().equals(email)) {
                System.out.println("Такой e-mail уже зарегистрирован.\nВведите другой e-mail: ");
                return false;
            }
        }
        return true;
    }
}

