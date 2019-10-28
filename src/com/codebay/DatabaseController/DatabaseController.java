package com.codebay.DatabaseController;

import com.codebay.user.User;
import com.google.gson.Gson;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DatabaseController {

    private ArrayList<User> userArray = new ArrayList<>();
    private String file;
    private Gson gson = new Gson();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DatabaseController(String file) throws IOException {
        this.file = file;
        String contentFile = readFile();
        User[] users = gson.fromJson(contentFile, User[].class);
        this.userArray.addAll(Arrays.asList(users));
    }

    private String readFile() throws IOException {
        FileReader reader = new FileReader(this.file);
        StringBuilder contentFile = new StringBuilder();
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
        for (User user : userArray) {
            if (user.active)
                System.out.println(user.name);
        }
    }

    public void listCityByLetter(String character) {
        for (User user : userArray) {
            if (user.city.indexOf(character) == 0)
                System.out.println(user.name);
        }
    }

    public void listByCreationDate(boolean ascending) throws ParseException {
        ArrayList<Date> dates = new ArrayList<>();

        for (User user: userArray)
            dates.add(format.parse(user.creationDate));
        if (ascending)
            Collections.sort(dates);
        else
            dates.sort(Collections.reverseOrder());

        for (Date date: dates)
            for (User user: userArray)
                if(date.compareTo(format.parse(user.creationDate)) == 0)
                    System.out.println("User: " + user.name + " Creation date: " + date);
    }

    public void addUser(String name, String surname, boolean active, String email, String city) throws IOException {
        User newUser = CreateUser(name, surname, active, email, city);

        String dataToWrite = "[";

        this.userArray.add(newUser);
        for (User user : this.userArray)
            dataToWrite += gson.toJson(user) + "," + "\n";

        dataToWrite= dataToWrite.substring(0,dataToWrite.length()-2);

        dataToWrite += "]";

        FileWriter writer = new FileWriter(this.file);
        writer.write(dataToWrite);
        writer.close();
    }

    private User CreateUser(String name, String surname, boolean active, String email, String city) {
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
