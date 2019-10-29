package com.codebay.DatabaseRepository;

import com.codebay.User.User;
import com.google.gson.Gson;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


public class DatabaseRepository {

    private ArrayList<User> users = new ArrayList<>();
    private String file;
    private Gson gson = new Gson();


    public DatabaseRepository(String file) {
        this.file = file;
        String contentFile = readFile();
        User[] users = gson.fromJson(contentFile, User[].class);
        this.users.addAll(Arrays.asList(users));
    }

    private String readFile(){

        try {
            FileReader reader = new FileReader(this.file);
            StringBuilder contentFile = new StringBuilder();

            //Read all content file to a string to create the array of users
            int ch;
            while ((ch = reader.read()) != -1) {
                char readCharacter = (char) ch;
                contentFile.append(readCharacter);
            }
            reader.close();
            return contentFile.toString();
        } catch (Exception e){
            System.out.println(e);
        }
        return "";

    }

    public void listActiveUsers() {
        System.out.println("List of active users");
        for (User user : users) {
            if (user.active)
                System.out.println(user);
        }
    }

    public void listCityByLetter(String character) {
        for (User user : users) {
            //Here check the first letter of the city and if there are igual show the user
            if (user.city.indexOf(character) == 0)
                System.out.println(user);
        }
    }

    public void listByCreationDate(boolean ascending) {
        ArrayList<LocalDateTime> dates = new ArrayList<>();
        for (User user : users) {
            LocalDateTime date = LocalDateTime.parse(user.creationDate);
            dates.add(date);
        }
        if (ascending)
            Collections.sort(dates);
        else
            dates.sort(Collections.reverseOrder());

        for (LocalDateTime date : dates)
            for (User user : users){
                LocalDateTime dateUser = LocalDateTime.parse(user.creationDate);
                if (date.compareTo(dateUser) == 0)
                    System.out.println(user);
            }
    }

    public void addUser(String name, String surname, boolean active, String email, String city, LocalDateTime date) {
        User newUser = createUser(name, surname, active, email, city, date);

        StringBuilder dataToWrite = new StringBuilder("[");

        this.users.add(newUser);

        try {
        //Here i overwrite all the users
        for (User user : this.users)
            dataToWrite.append(gson.toJson(user)).append(",").append("\n");

        //Here i eliminate the last ',' to avoid problems in the read.
        dataToWrite = new StringBuilder(dataToWrite.substring(0, dataToWrite.length() - 2));
        dataToWrite.append("]");

        FileWriter writer = new FileWriter(this.file);
        writer.write(dataToWrite.toString());
        writer.close();
    }catch (Exception e){
            System.out.println("error" + e);
        }
    }

    private User createUser(String name, String surname, boolean active, String email, String city, LocalDateTime d) {
        String date = d.toString();
        return new User(name, surname, active, email, city, date);
    }

}
