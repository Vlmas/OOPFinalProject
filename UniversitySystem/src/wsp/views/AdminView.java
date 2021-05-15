package wsp.views;

import wsp.database.Database;
import wsp.models.Admin;
import wsp.utils.GlobalReader;
import java.io.IOException;
import java.util.Date;

public class AdminView extends UserView {
    private Admin admin;

    public AdminView() {

    }

    public AdminView(Admin admin) {
        this();
        this.admin = admin;
    }

    public void addUser() throws IOException {
        System.out.println("Choose which type of user you want to add, or X to cancel:");
        System.out.println("|1| Admin\n|2| Manager\n|3| Teacher\n|4| Librarian\n|5| Student\n|X| Cancel");

        String chosenUser = GlobalReader.reader.readLine();
    }

    public void removeUser() {

    }

    public void updateUser() {

    }

    public void viewUserActions() {

    }

    @Override
    public void start() throws InterruptedException, IOException {
        greet();
        Thread.sleep(500);

        System.out.println("Select which type of action you want to perform:");
        System.out.println("|1| Add user\n|2| Delete user\n|3| Update user\n|4| View logs about user actions\n|X| Cancel");
        String choice = GlobalReader.reader.readLine();

        performAction(choice);
    }

    @Override
    public void performAction(String choice) throws IOException {
        switch(choice.toLowerCase()) {
            case "1" -> addUser();
            case "2" -> removeUser();
            case "3" -> updateUser();
            case "4" -> viewUserActions();
            case "5", "x", "q", "exit", "quit" -> {

            }
        }
    }

    @Override
    public void greet() {
        System.out.println("Welcome, " + admin.getName() + "!");
        Database.getInstance().addUserAction("User " + admin.getName() + " (Admin) logged in at" + new Date().getTime());
    }
}