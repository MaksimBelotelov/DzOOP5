package org.example.view;

import org.example.controller.AccountController;
import org.example.controller.UserController;
import org.example.model.Account;
import org.example.model.User;

public class UserMenu extends Menu {
    private User currentUser;
    public UserMenu(User user, UserController userController) {
        super("Меню пользователя");
        AccountController accountController = new AccountController();
        this.currentUser = user;
        System.out.println("Здравствуйте, " + user.getName() + " !");

        this.menuItems.add(new MenuItem("Открыть новый счет") {
            @Override
            public void processItem() {
                currentUser.getAccountIds().add(accountController.addNewAccount());
                System.out.println("Вам открыт новый счёт.");
            }
        });

        this.menuItems.add(new MenuItem("Показать все мои счета") {
            @Override
            public void processItem() {
                accountController.showMeMyAccounts(currentUser);
            }
        });

        this.menuItems.add(new MenuItem("Пополнить счет") {
            @Override
            public void processItem() {

            }
        });

        this.menuItems.add(new MenuItem("Снять деньги со счёта") {
            @Override
            public void processItem() {

            }
        });

        this.menuItems.add(new MenuItem("Перевести деньги между счетами") {
            @Override
            public void processItem() {

            }
        });

        this.menuItems.add(new MenuItem("Закрыть счет") {
            @Override
            public void processItem() {

            }
        });

        this.menuItems.add(new MenuItem("Удалить аккаунт") {
            @Override
            public void processItem() {
                userController.remove(currentUser);
                userController.saveUsersAndExit();
                accountController.saveAccountAndExit();
                System.out.println("Пользователь " + currentUser.getName() + " удален.");
            }
        });

        this.menuItems.add(new MenuItem("Выход") {
            @Override
            public void processItem() {
                userController.saveUsersAndExit();
                accountController.saveAccountAndExit();
            }
        });
    }

}
