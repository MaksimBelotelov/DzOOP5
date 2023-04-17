package org.example.view;

import org.example.controller.UserController;
import org.example.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu() {
       super("Добро пожаловать в первый оффлайн банк!\nГлавное меню");
       UserController userController = new UserController();
       this.menuItems.add(new MenuItem("Вход") {
            @Override
            public void processItem() {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите ваш e-mail: ");
                String inpEmail = scanner.nextLine();
                System.out.print("Введите пароль:");
                String inpPassword = scanner.nextLine();
                User currentUser = userController.loginUser(inpEmail, inpPassword);
                if(currentUser != null) {
                    UserMenu userMenu = new UserMenu(currentUser, userController);
                    userMenu.startMenu();
                }
                else
                    System.out.println("Не удалось войти");
            }
       });

       this.menuItems.add(new MenuItem("Регистрация") {
            @Override
            public void processItem() {
                userController.addNewUser();
                System.out.println("Спасибо за регистрацию!\n" +
                        "Теперь вы можете войти, используя e-mail и пароль.");
            }
       });

       this.menuItems.add(new MenuItem("Выход") {
            @Override
            public void processItem() {
                userController.saveUsersAndExit();
                System.out.println("До свидания!");
                System.exit(0);
            }
       });


    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();

        mainMenu.startMenu();
    }
}
