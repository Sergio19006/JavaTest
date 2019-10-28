package com.codebay;
import com.codebay.DatabaseController.DatabaseController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        DatabaseController database = new DatabaseController("/home/sergio/IdeaProjects/JavaTest/src/com/codebay/test.txt");
        database.listActiveUsers();

        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a character to find a City");
        String character = br.readLine();
        database.listCityByLetter(character);
        System.out.println("Enter 'ascending' or 'descending' to sort users by Creation Date");
        String option = br.readLine();
        if(option.equals("ascending"))
            database.listByCreationDate(true);
        else
            database.listByCreationDate(false);

        ArrayList<String> data = database.menuToAddUser();
        database.addUser(data.get(0),data.get(1),Boolean.parseBoolean(data.get(2)),data.get(3),data.get(4));
    }
}
