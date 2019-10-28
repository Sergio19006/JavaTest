package com.codebay.DatabaseController;

import com.codebay.user.User;
import com.google.gson.Gson;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


public class DatabaseController {

    private ArrayList<User> users = new ArrayList<>();
    private String file;
    private Gson gson = new Gson();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DatabaseController(String file) throws IOException {
        this.file = file;
        String contentFile = readFile();
        User[] users = gson.fromJson(contentFile, User[].class);
        this.users.addAll(Arrays.asList(users));
    }

    private String readFile() throws IOException {
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

    public void listByCreationDate(boolean ascending) throws ParseException {
        ArrayList<Date> dates = new ArrayList<>();

        for (User user : users)
            dates.add(format.parse(user.creationDate));
        if (ascending)
            Collections.sort(dates);
        else
            dates.sort(Collections.reverseOrder());

        for (Date date : dates)
            for (User user : users)
                if (date.compareTo(format.parse(user.creationDate)) == 0)
                    System.out.println(user);
    }

    public void addUser(String name, String surname, boolean active, String email, String city) throws IOException {
        User newUser = createUser(name, surname, active, email, city);

        StringBuilder dataToWrite = new StringBuilder("[");

        this.users.add(newUser);

        //Here i overwrite all the users
        for (User user : this.users)
            dataToWrite.append(gson.toJson(user)).append(",").append("\n");

        //Here i eliminate the last ',' to avoid problems in the read.
        dataToWrite = new StringBuilder(dataToWrite.substring(0, dataToWrite.length() - 2));
        dataToWrite.append("]");

        FileWriter writer = new FileWriter(this.file);
        writer.write(dataToWrite.toString());
        writer.close();
    }

    private User createUser(String name, String surname, boolean active, String email, String city) {
        String date = format.format(new Date());
        return new User(name, surname, active, email, city, date);
    }

    public ArrayList<String> menuToAddUser() throws IOException {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> userData = new ArrayList<>();
        System.out.println("Enter the user's name:");
        String dummy = br.readLine();
        userData.add(dummy);
        System.out.println("Enter the user's surname:");
        dummy = br.readLine();
        userData.add(dummy);
        System.out.println("Enter 'true' or 'false' to the activate attribute:");
        dummy = br.readLine();
        userData.add(dummy);
        System.out.println("Enter the user's email:");
        dummy = br.readLine();
        userData.add(dummy);
        System.out.println("Enter the user's city:");
        dummy = br.readLine();
        userData.add(dummy);
        return userData;
    }
}
