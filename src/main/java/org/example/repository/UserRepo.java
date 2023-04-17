package org.example.repository;

import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.valueOf;

public class UserRepo {
    public static final String USERSPATH = "UsersData.txt";

    public ArrayList<User> loadUsersFromBase() {
        ArrayList<User> users = new ArrayList<>();

        try {
            File file = new File(USERSPATH);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String email = reader.readLine();
            while (email != null){
                String surname = reader.readLine();
                String name = reader.readLine();
                String passWord = reader.readLine();
                String[] accountsStr = reader.readLine().split(", ");
                ArrayList<Integer> accountsInt = new ArrayList<>();
                for(int i = 0; i < accountsStr.length; i++) {
                    try {
                        accountsInt.add(valueOf(accountsStr[i]));
                    } catch (NumberFormatException exc) {}
                }
                users.add(new User(surname, name, email, passWord, accountsInt));
                email = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public void saveUsersToBase(ArrayList<User> list) {
        try(FileWriter writer = new FileWriter(USERSPATH, false)) {
            for(User user : list){
                writer.append(user.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
