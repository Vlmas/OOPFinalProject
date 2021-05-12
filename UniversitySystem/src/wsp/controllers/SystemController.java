package wsp.controllers;

import wsp.database.Database;
import wsp.exceptions.FailedLogInException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class SystemController {
    BufferedReader inputReader;
    String userType;

    {
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public SystemController() {
        displayGreetingMessage();
    }

    public void start() throws IOException, FailedLogInException, InterruptedException {
        userType = chooseLogInUser();

        try {
            logIn(userType);
        } catch(FailedLogInException exc) {
            System.out.println(exc.getMessage());
            start();
        }
    }

    public void displayGreetingMessage() {
        System.out.println("Welcome to KBTU University System!\n");
    }

    public String chooseLogInUser() throws IOException, InterruptedException {
        System.out.println("Please, select the type of user to log in, or X to exit:");
        System.out.println("|1| Admin\n|2| Manager\n|3| Teacher\n|4| Librarian\n|5| Student\n|X| Exit");

        String choice = inputReader.readLine();

        switch(choice.toLowerCase()) {
            case "1" -> {
                return "admin";
            }
            case "2" -> {
                return "manager";
            }
            case "3" -> {
                return "teacher";
            }
            case "4" -> {
                return "librarian";
            }
            case "5" -> {
                return "student";
            }
            case "x", "q", "exit", "quit" -> {
                finish();
                System.exit(0);
                return "exited";
            }
            default -> {
                return "unknown";
            }
        }
    }

    public void logIn(String userType) throws IOException, FailedLogInException {
        if(userType.equals("unknown")) {
            throw new FailedLogInException("Unknown or non-existing user, please, try again");
        }
        String login, password;
        boolean succeeded = false;

        System.out.print("Enter login: ");
        login = readLogin();
        System.out.print("Enter password: ");
        password = readPassword();

        for(Map.Entry<String, String> loginPassword : Database.getInstance().getUserLoginsAndPasswords(userType).entrySet()) {
            if(loginPassword.getKey().equals(login)) {
                if(loginPassword.getValue().equals(password)) {
                    System.out.println("It worked");
                    succeeded = true;
                }
            }
        }

        if(!succeeded) {
            throw new FailedLogInException("User either doesn't exist or you have selected the wrong category, please, try again");
        }
    }

    public void finish() throws InterruptedException {
        System.out.print("Logging out");
        Thread.sleep(800);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.println(" Goodbye!");
    }

    public String readLogin() throws IOException {
        return inputReader.readLine();
    }

    public String readPassword() throws IOException {
        return inputReader.readLine();
    }
}