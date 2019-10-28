package com.codebay.DatabaseController;

import com.codebay.DatabaseRepository.DatabaseRepository;
import com.sun.jdi.event.ExceptionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class DatabaseController {

    DatabaseRepository database;
    private BufferedReader br;
    private Object ParseException;

    public DatabaseController(String path) throws IOException {
        database = new DatabaseRepository(path);
        this.br = new BufferedReader(new InputStreamReader(System.in));
        /*
        ArrayList<String> data = database.menuToAddUser();
        database.addUser(data.get(0),data.get(1),Boolean.parseBoolean(data.get(2)),data.get(3),data.get(4));
        */
    }

    public void dispatch(int option) throws IOException {
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
        } catch (Exception e){
            System.out.println("Error. IOException");
            System.exit(-1);
        }
    }

    private void listByCreationDate() {
        try {
            System.out.println("Enter 'ascending' or 'descending' to sort users by Creation Date");
            String option = br.readLine();
            if (option.equals("ascending"))
                database.listByCreationDate(true);
            else
                database.listByCreationDate(false);
        } catch (Exception e) {
            if (e == ParseException) {
                System.out.println("ParseException");
                System.exit(-1);
            }
        }
    }
}
