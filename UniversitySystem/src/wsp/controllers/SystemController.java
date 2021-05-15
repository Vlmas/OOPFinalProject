package wsp.controllers;

import wsp.database.Database;
import wsp.exceptions.FailedLogInException;
import wsp.models.*;
import wsp.utils.GlobalReader;
import wsp.views.*;
import java.io.IOException;
import java.util.Map;

public class SystemController {
    public SystemController() throws InterruptedException {
        displayGreetingMessage();
    }

    public void start() throws IOException, FailedLogInException, InterruptedException {
        String userType = chooseLogInUser();

        try {
            logIn(userType);
        } catch(FailedLogInException exc) {
            System.out.println(exc.getMessage());
            start();
        }
    }

    public void displayGreetingMessage() throws InterruptedException {
        System.out.println("Welcome to KBTU University System!\n");
        Thread.sleep(1000);
    }

    public String chooseLogInUser() throws IOException, InterruptedException {
        System.out.println("\nPlease, select the type of user to log in, or X to exit:");
        System.out.println("--------------\n|1| Admin\n|2| Manager\n|3| Teacher\n|4| Librarian\n|5| Student\n|X| Exit\n--------------");

        String choice = GlobalReader.reader.readLine();

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
                return "exited";
            }
            default -> {
                return "unknown";
            }
        }
    }

    public void logIn(String userType) throws IOException, FailedLogInException, InterruptedException {
        Thread.sleep(500);

        if(userType.equals("unknown")) {
            throw new FailedLogInException("Unknown or non-existing user, please, try again");
        }
        if(!checkIfUsersExist(userType)) {
            throw new FailedLogInException("There are currently no users of such type, please, select other one");
        }
        User user = null;
        boolean succeeded = false;

        System.out.println("You are logging in as " + userType + "..");
        System.out.print("Enter login: ");
        String login = readLogin();
        System.out.print("Enter password: ");
        String password = readPassword();

        for(Map.Entry<String, String> loginPassword : Database.getInstance().getUserLoginsAndPasswords(userType).entrySet()) {
            if(loginPassword.getKey().equals(login) && loginPassword.getValue().equals(password)) {
                user = Database.getInstance().getUserByLoginAndPassword(login, password);
                succeeded = true;
            }
        }

        if(!succeeded) {
            throw new FailedLogInException("User either doesn't exist or you have selected the wrong category, please, try again");
        }
        runUser(user);
    }

    public void finish() throws InterruptedException {
        System.out.print("Logging out");
        Thread.sleep(600);
        System.out.print(".");
        Thread.sleep(800);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(800);
        System.out.println(" Goodbye!");
        System.exit(0);
    }

    public void runUser(User user) throws InterruptedException, IOException {
        UserView view = null;

        if(user instanceof Admin) {
            view = new AdminView((Admin) user);
        }
        else if(user instanceof Manager) {
            view = new ManagerView((Manager) user);
        }
        else if(user instanceof Teacher) {
            view = new TeacherView((Teacher) user);
        }
        else if(user instanceof Librarian) {
            view = new LibrarianView((Librarian) user);
        }
        else if(user instanceof Student) {
            view = new StudentView((Student) user);
        }

        assert(view != null);
        view.start();
    }

    public String readLogin() throws IOException {
        return GlobalReader.reader.readLine();
    }

    public String readPassword() throws IOException {
        return GlobalReader.reader.readLine();
    }

    public boolean checkIfUsersExist(String userType) {
        return !Database.getInstance().getUserLoginsAndPasswords(userType).isEmpty();
    }

    public void save() {}

    public void load() {}
}