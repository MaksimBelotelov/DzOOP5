package org.example.view;

import org.example.controller.AccountController;
import org.example.controller.UserController;
import org.example.model.Account;
import org.example.model.User;

import java.util.Scanner;

public class UserMenu extends Menu {
    private User currentUser;
    public UserMenu(User user, UserController userController) {
        super("Меню пользователя");
        AccountController accountController = new AccountController();
        this.currentUser = user;
        System.out.println("\nЗдравствуйте, " + user.getName() + " !");

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
                if(currentUser.getAccountIds().isEmpty()) {
                    System.out.println("У вас нет открытых счетов.\n");
                }
                else {
                    System.out.println("Ваши счета: ");
                    accountController.showMeMyAccounts(currentUser);
                }
            }
        });

        this.menuItems.add(new MenuItem("Пополнить счет") {
            @Override
            public void processItem() {
                System.out.println("Введите номер счёта для пополнения: ");
                Scanner scanner = new Scanner(System.in);
                Integer numberOfAccToFind = scanner.nextInt();
                Account currentAcc = accountController.findAccountByNumber(numberOfAccToFind);
                if(accountController.isMyAccount(currentUser, currentAcc)){
                    System.out.println("Введите сумму для пополнения: ");
                    Double amountToDeposit = scanner.nextDouble();
                    if(amountToDeposit > 0) {
                        currentAcc.setBalance(currentAcc.getBalance() + amountToDeposit);
                        System.out.println("\nСчет № " + currentAcc.getId() + " пополнен." +
                                "\nНовый баланс: " + currentAcc.getBalance() + "\n");
                    }
                    else {
                        System.out.println("Нельзя пополнить счёт отрицательной суммой.");
                    }
                }
                else {
                    System.out.println("У вас нет счёта с таким номером.");
                }
            }
        });

        this.menuItems.add(new MenuItem("Снять деньги со счёта") {
            @Override
            public void processItem() {
                System.out.println("Введите номер счёта для списания: ");
                Scanner scanner = new Scanner(System.in);
                Integer numberOfAccToFind = scanner.nextInt();
                Account currentAcc = accountController.findAccountByNumber(numberOfAccToFind);
                if((currentAcc != null) && accountController.isMyAccount(currentUser, currentAcc)){
                    System.out.println("Введите сумму для списания: ");
                    Double amountToOff = scanner.nextDouble();
                    if(amountToOff <= currentAcc.getBalance()) {
                        currentAcc.setBalance(currentAcc.getBalance() - amountToOff);
                        System.out.println("\nСчет № " + currentAcc.getId() + " списание." +
                                "\nНовый баланс: " + currentAcc.getBalance() + "\n");
                    }
                    else {
                        System.out.println("Не достаточно средств. Текущий баланс: " + currentAcc.getBalance());
                    }
                }
                else {
                    System.out.println("У вас нет счёта с таким номером.");
                }
            }
        });

        this.menuItems.add(new MenuItem("Перевести деньги между счетами") {
            @Override
            public void processItem() {
                System.out.println("Введите номер счёта для списания: ");
                Scanner scanner = new Scanner(System.in);
                Integer numberOfAccToGive = scanner.nextInt();
                Account accToGive = accountController.findAccountByNumber(numberOfAccToGive);
                if((accToGive != null) && (accountController.isMyAccount(currentUser, accToGive))) {
                    System.out.println("Введите номер счёта для зачисления: ");
                    Integer numberOfAccToReceive = scanner.nextInt();
                    Account accToReceive = accountController.findAccountByNumber(numberOfAccToReceive);
                    if(accToReceive != null) {
                        System.out.println("Введите сумму для списания: ");
                        Double amountToOff = scanner.nextDouble();
                        if (amountToOff > accToReceive.getBalance()) {
                            System.out.println("Не достаточно средств для операции. Текущий баланс: " +
                                    accToGive.getBalance());
                        } else {
                            accToGive.setBalance(accToGive.getBalance() - amountToOff);
                            accToReceive.setBalance(accToReceive.getBalance() + amountToOff);
                            System.out.println("Перевод выполнен.");
                            System.out.println("Получатель: " + userController.whoIsOwner(accToReceive).getSurname());
                        }
                    }
                    else {
                        System.out.println("Не найден счет получателя.");
                    }
                }
                else {
                    System.out.println("У вас нет счёта с таким номером.");
                }
            }
        });

        this.menuItems.add(new MenuItem("Закрыть счет") {
            @Override
            public void processItem() {
                System.out.println("Введите номер счёта для удаления: ");
                Scanner scanner = new Scanner(System.in);
                Integer numberOfAccToDel = scanner.nextInt();
                Account currentAcc = accountController.findAccountByNumber(numberOfAccToDel);
                if(accountController.isMyAccount(currentUser, currentAcc)){
                    if(currentAcc.getBalance() > 0) {
                        System.out.println("\nНа счёте есть деньги. Для закрытия необходимо снять их.");
                    }
                    else {
                        currentUser.getAccountIds().remove((Integer)numberOfAccToDel);
                        accountController.deleteAccount(currentAcc);
                        System.out.println("Счёт №: " + numberOfAccToDel + " удалён.");
                    }
                }
                else {
                    System.out.println("У вас нет счёта с таким номером.");
                }
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
