package com.codebay.DatabaseController;

import com.codebay.DatabaseRepository.DatabaseRepository;
import com.codebay.Helper.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseController {

    private DatabaseRepository database;
    private BufferedReader br;

    public DatabaseController(String path){
        database = new DatabaseRepository(path);
        try {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void dispatch(int option) {
        switch (option) {
            case 1:
                listActiveUsers();
                break;
            case 2:
                listCityByLetter();
                break;
            case 3:
                listByCreationDate();
                break;
            case 4:
                addUser();
                break;
        }
    }

    private void listActiveUsers() {
        database.listActiveUsers();
    }

    private void listCityByLetter() {
        try {
            System.out.println("Insert a character: ");
            String character = this.br.readLine();
            if (character.length() == 1)
                database.listCityByLetter(character);
            else {
                System.out.println("Error. only one character permited");
                System.exit(-1);
            }
        } catch (Exception e) {
            System.out.println("Error. IOException");
            System.exit(-1);
        }
    }

    private void listByCreationDate() {
        try {
            boolean option;
            option = getSortedBy();
            database.listByCreationDate(option);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addUser() {
        LocalDateTime date = LocalDateTime.now();
        ArrayList<String> userData = getUserData();
        database.addUser(userData.get(0), userData.get(1), Boolean.parseBoolean(userData.get(2)), userData.get(3), userData.get(4), date);
    }

    private ArrayList<String> getUserData() {
        ArrayList<String> userData = new ArrayList<>();

        try {
            userData.add(getName("name"));
            userData.add(getName("surname"));
            userData.add(getActivate());
            userData.add(getEmail());
            userData.add(getCity());
            return userData;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //Error here. Ask Jose. Need Refactor
    private String getName(String type) {
        String data = "";
        boolean error = true;
        try {
            while (error) {
                error = false;
                System.out.println("Enter the user's " + type + " : ");
                data = br.readLine();
                if (Helper.containsNumbers(data)) { //doesnt work :(
                    System.out.println("Please enter a " + type + " without numbers");
                    error = true;
                }
                if (Helper.tooShort(data)) {
                    System.out.println("Please enter a " + type + " with at least 3 character");
                    error = true;
                }
            }
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private String getActivate() {
        String data = "";
        boolean error = true;
        try {
            while (error) {
                error = false;
                System.out.println("Enter 'true' or 'false' to set the activate params");
                data = br.readLine();
                if (!Helper.trueOrFalse(data)) {
                    System.out.println("Please enter a correct option");
                    error = true;
                }
            }
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private String getEmail() {
        String data = "";
        boolean error = true;
        try {
            while (error) {
                error = false;
                System.out.println("Enter your email");
                data = br.readLine();
                if (!Helper.isEmail(data)) {
                    System.out.println("Please enter a correct email");
                    error = true;
                }
            }
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private String getCity() {
        String data = "";
        boolean error = true;
        try {
            while (error) {
                error = false;
                System.out.println("Enter your city");
                data = br.readLine();
                if (Helper.tooShort(data)) {
                    System.out.println("Please enter a city with at least 3 character");
                    error = true;
                }
            }
            return data;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private boolean getSortedBy() {
        String option = "";
        boolean op = false;
        boolean error = true;
        try {
            while (error) {
                error = false;
                System.out.println("Enter 'ascending' or 'descending' to sort users by Creation Date.");
                option = br.readLine();
                if (!Helper.isAscendingOrDescending(option)) {
                    System.out.println("Enter a correct option");
                    error = true;
                }
                else {
                    op = option.equals("ascending");
                }
            }
            return op;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
