package com.codebay;
import com.codebay.DatabaseController.DatabaseController;

import java.io.IOException;
import java.text.ParseException;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        DatabaseController database = new DatabaseController("/home/sergio/IdeaProjects/JavaTest/src/com/codebay/test.txt");
        database.listActiveUsers();
        database.listCityByLetter('L');
        database.listByCreationDate(true);
        //database.addUser("David","Hernandez",true,"dhernandez@gmail.com","Lepe");
    }
}
