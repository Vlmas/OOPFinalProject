package wsp.views;

import wsp.database.Database;
import wsp.models.Admin;
import wsp.models.User;
import wsp.utils.GlobalReader;
import wsp.utils.UserFactory;
import java.io.IOException;
import java.util.*;

public class AdminView extends UserView {
    private Admin admin;

    public AdminView() {}

    public AdminView(Admin admin) {
        this.admin = admin;
        greet();
    }

    public void addUser() throws IOException {
        System.out.println("Choose which type of user you want to add, or X to cancel:");
        System.out.println("|1| Admin\n|2| Manager\n|3| Teacher\n|4| Librarian\n|5| Student\n|X| Cancel");
        String chosenUser = determineUser(GlobalReader.reader.readLine());

        if(chosenUser.equals("exit") || chosenUser.equals("unknown")) {
            System.out.println("Operation was canceled");
            return;
        }
        UserFactory factory = new UserFactory();
        User user = factory.createUser(chosenUser);
        admin.addUser(user);

        System.out.println(user.getName() + " " + user.getLogin() + " " + user.getPassword());
    }

    private String determineUser(String chosenUser) {
        switch(chosenUser.toLowerCase()) {
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
                return "exit";
            }
            default -> {
                return "unknown";
            }
        }
    }

    public void removeUser() {

    }

    public void updateUser() {

    }

    public void viewUserActions() throws IOException {
        System.out.println("User log actions up to now:");

        ArrayList<String> actions = admin.getUserActions();

        int index = 1;
        for(String action : actions) {
            System.out.println(index + ") " + action);
            index++;
        }
        System.out.println("\nDo you wish to clear the history of log actions? Any key to answer No");
        System.out.println("|1| Yes\n|X| No");
        String choice = GlobalReader.reader.readLine();
        if(choice.equals("1")) {
            actions.clear();
            System.out.println("Action history has been cleared!");
        }
    }

    public void viewUsers() {
        HashSet<User> users = admin.getUsers();

        int index = 1;
        for(User user : users) {
            System.out.println(index + ") " + user.getSurname()
                    + " " + user.getName().charAt(0) + ". (" + user.getClass().getSimpleName() + ")");
            index++;
        }
    }

    public void sendMessage() {

    }

    @Override
    public boolean start() throws InterruptedException, IOException {
        Thread.sleep(500);

        System.out.println("\nSelect which type of action you want to perform, or X to cancel:");
        System.out.println("|1| View users\n|2| Add user\n|3| Delete user\n" +
                "|4| Update user\n|5| View logs about user actions\n|6| Send message\n|X| Cancel");
        String choice = GlobalReader.reader.readLine();

        if(performAction(choice)) {
            return start();
        } else {
            return true;
        }
    }

    @Override
    public boolean performAction(String choice) throws IOException {
        switch(choice.toLowerCase()) {
            case "1" -> {
                viewUsers();
                return true;
            }
            case "2" -> {
                addUser();
                return true;
            }
            case "3" -> {
                removeUser();
                return true;
            }
            case "4" -> {
                updateUser();
                return true;
            }
            case "5" -> {
                viewUserActions();
                return true;
            }
            case "6" -> {
                sendMessage();
                return true;
            }
            case "7", "x", "q", "exit", "quit" -> {
                System.out.println("Logging out.. Goodbye, " + admin.getName() + "!");
                return false;
            }
            default -> {
                System.out.println("Wrong operation was selected, please, choose the correct one");
                return true;
            }
        }
    }

    @Override
    public void greet() {
        System.out.println("Welcome, " + admin.getName() + "!\n");
        Database.getInstance().addUserAction("User " + admin.getName() + " (Admin) logged in at " + new Date().toString());
    }
}