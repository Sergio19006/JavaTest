package com.codebay.menu;

import com.codebay.DatabaseController.DatabaseController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {

    private BufferedReader br;
    private Object IOException;

    public Menu() {
        try {
            this.br = new BufferedReader(new InputStreamReader(System.in));
            String path = getPath();
            DatabaseController controller = new DatabaseController(path);
            handleMenu(controller);
        } catch (Exception e) {
            if (e == IOException) {
                System.out.println("Error. IOException");
                System.exit(-1);
            }
        }
    }

    private void handleMenu(DatabaseController controller) {
        int option;
        try {
            do {
                printMenu();
                String buffer = br.readLine();
                option = Integer.parseInt(buffer);
                controller.dispatch(option);
            } while (option == 1 || option == 2 || option == 3 || option == 4);
        } catch (Exception e) {
            if (e == IOException) {
                System.out.println("Error. IOException");
                System.exit(-1);
            }
        }
    }

    private void printMenu() {
        System.out.println("\n");
        System.out.println("1. List activates users");
        System.out.println("2. List by initial character of city");
        System.out.println("3. List users sorted by ascending or descending creation date");
        System.out.println("4. Add user to the database");
        System.out.println("Press any key to exit");
        System.out.println("Insert a option");
    }

    private String getPath() {
        try {
            System.out.println("Introduce the path of the file");
            String path = this.br.readLine();
            if (path.contains("/home/sergio"))
                return path;
            else {
                System.out.println("Error in the path. Retry to introduce a correct path");
                System.exit(-1);
            }
        } catch (Exception e) {
            if (e == IOException) {
                System.out.println("Error. IOException");
                System.exit(-1);
            }
        }
        return "";
    }
}
