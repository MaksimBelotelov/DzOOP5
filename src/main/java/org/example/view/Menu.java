package org.example.view;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private String title;
    protected ArrayList<MenuItem> menuItems = new ArrayList<>();
    private Scanner userInput = new Scanner(System.in);

    public Menu(String title){
        this.title = title;

    }

    public Menu() {
        this("Здесь должен быть заголовок вашего меню.");
    }

    public void startMenu() {
        int choice = 0;
        System.out.println(title);
        while(true) {
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println(i + ". " + menuItems.get(i).getItemName());
            }
            if((choice = userInput.nextInt()) < menuItems.size() && choice >= 0)
                menuItems.get(choice).processItem();
            else
                System.out.println("Incorrect input! Необходимо вести цифру от 0 до " + (menuItems.size()-1) + "\n");
            if(menuItems.get(choice).getItemName().equals("Выход") ||
                    menuItems.get(choice).getItemName().equals("Удалить аккаунт")) break;
        }
    }
}
