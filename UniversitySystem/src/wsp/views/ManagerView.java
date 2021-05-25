package wsp.views;

import wsp.comparators.*;
import wsp.database.Database;
import wsp.enums.AttestationSeason;
import wsp.models.*;
import wsp.utils.Util;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ManagerView extends UserView {
    private Manager manager;

    public ManagerView() {
        setMenu(
                "---------------------------------\n" +
                "|1| View students\n" +
                "|2| View teachers\n" +
                "|3| Approve student registration\n" +
                "|4| Add course\n" +
                "|5| Assign a course to a teacher\n" +
                "|6| Read messages\n" +
                "|7| Send message\n" +
                "|8| Create statistical report\n" +
                "|9| Manage news\n" +
                "|X| Logout\n" +
                "---------------------------------"
        );
    }

    public ManagerView(Manager manager) {
        this();
        this.manager = manager;
        greet();
    }

    @Override
    public void greet() {
        System.out.println("Welcome, " + manager.getName() + "!");
        Database.getInstance().addUserAction("User " + manager.getName() + " (Manager) logged in at " + new Date().toString());
    }

    @Override
    public boolean performAction(String choice) throws InterruptedException, IOException {
        switch(choice.toLowerCase()) {
            case "1" -> viewStudents();
            case "2" -> viewTeachers();
            case "3" -> approveRegistration();
            case "4" -> addCourse();
            case "5" -> assignCourseToTeacher();
            case "6" -> sendMessage();
            case "7" -> readMessages();
            case "8" -> createReport();
            case "9" -> manageNews();
            case "10", "x", "q", "exit", "quit" -> {
                System.out.println("Logging out.. Goodbye, " + manager.getName() + "!");
                return false;
            }
            default -> {
                System.out.println("Wrong operation was selected, please, choose the correct one");
                return true;
            }
        }
        return true;
    }

    public void viewStudents() throws IOException {
        ArrayList<Student> students = new ArrayList<>(Database.getInstance().getStudents());
        System.out.println("Choose order of displaying:\n|1| By name\n|2| By GPA\n|3| By ID");
        switch(Util.reader.readLine()) {
            case "1" -> students.sort(new NameComparator());
            case "2" -> students.sort(new GpaComparator());
            case "3" -> students.sort(new IdComparator());
        }
        System.out.println("All students:");
        Util.printList(students);
    }

    public void viewTeachers() throws IOException {
        ArrayList<Teacher> teachers = new ArrayList<>(Database.getInstance().getTeachers());
        System.out.println("Choose order of displaying:\n|1| By name\n|2| By salary");
        switch(Util.reader.readLine()) {
            case "1" -> teachers.sort(new NameComparator());
            case "2" -> teachers.sort(new SalaryComparator());
        }
        System.out.println("All teachers:");
        Util.printList(teachers);
    }

    public void approveRegistration() throws InterruptedException, IOException {
        HashMap<Student, ArrayList<Course>> requests = Database.getInstance().getCourseRegistrationRequests();

        if(!requests.isEmpty()) {
            System.out.println("Incoming requests of students: ");

            int index = 1;
            for(Map.Entry<Student, ArrayList<Course>> request : requests.entrySet()) {
               System.out.println(index + ") " + request.getKey().getName() + " " + request.getKey().getSurname());
               index++;
            }
            System.out.print("\nSelect which student's request to process: ");
            int choice = Util.parseChoice(Util.reader.readLine());

            if(Util.isInRange(choice, 0, requests.size() - 1)) {
                index = 0;
                for(Map.Entry<Student, ArrayList<Course>> request : requests.entrySet()) {
                    if(index == choice) {
                        System.out.println("Student: " + request.getKey().getName() + " " + request.getKey().getSurname() + ". " + request.getKey().getFaculty().getName());
                        processRequest(request.getKey(), request.getValue());
                        break;
                    }
                }
            } else {
                System.out.println("Select from the given list!");
            }
        }
        System.out.println("No incoming requests yet..");
        Thread.sleep(500);
    }

    public void addCourse() {}

    public void assignCourseToTeacher() {}

    public void sendMessage() {}

    public void readMessages() {}

    public void createReport() {
        ArrayList<Student> students = new ArrayList<>(Database.getInstance().getStudents());
        String fileName = "report.txt";
        double averageGpa = 0;
        int fitStudents = 0;
        int mcmStudents = 0;
        int bsStudents = 0;
        String performance;

        for(Student student : students) {
            averageGpa += student.getGpa();
            switch(student.getFaculty().getName()) {
                case FIT -> fitStudents++;
                case SECMC -> mcmStudents++;
                case BS -> bsStudents++;
            }
        }
        averageGpa /= students.size();
        if(averageGpa >= 4.0 && averageGpa <= 3.5) {
            performance = "Excellent";
        }
        else if(averageGpa >= 3.0) {
            performance = "Good";
        }
        else if(averageGpa >= 2.0) {
            performance = "Acceptable";
        } else {
            performance = "Poor";
        }

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
            writer.write(String.format("Total students: %d\n", students.size()));
            writer.write(String.format("Total FIT students: %d\n", fitStudents));
            writer.write(String.format("Total SECMC students: %d\n", mcmStudents));
            writer.write(String.format("Total BS students: %d\n", bsStudents));
            writer.write(String.format("Average GPA: %f\n", averageGpa));
            writer.write(String.format("Academic performance status: %s", performance));
            writer.close();
        } catch(IOException exc) {
            System.out.println("Unable to create a report, " + exc.getMessage());
        }
    }

    public void manageNews() {}

    public void processRequest(Student student, ArrayList<Course> courses) throws IOException, InterruptedException {
        System.out.println("Check the request for correctness, if you find a mistake, reject the request. Courses:");

        Util.printList(courses);
        System.out.println("\nType the word:\n|V| Accept\n|X| Reject");
        String choice = Util.reader.readLine();
        Thread.sleep(500);
        if(choice.equalsIgnoreCase("accept")) {
            System.out.println("Request was accepted, we are almost there");
            acceptRequest(student, courses);
        }
        else if(choice.equalsIgnoreCase("reject")) {
            System.out.println("Request was rejected");
            Database.getInstance().getCourseRegistrationRequests().remove(student);
        } else {
            System.out.println("Unknown command");
        }
    }

    private void acceptRequest(Student student, ArrayList<Course> courses) throws IOException {
        System.out.print("Enter year: ");
        int year = Integer.parseInt(Util.reader.readLine());
        System.out.println("Enter season:\n|1| Fall\n|2| Spring");
        AttestationSeason season = (Util.reader.readLine().equals("1")) ? AttestationSeason.FALL : AttestationSeason.SPRING;
        Period period = new Period(year, season);

        student.getTranscript().getMarks().putIfAbsent(period, new HashMap<>());
        for(Course course: courses) {
            student.getTranscript().getMarks().get(period).put(course, new Mark());
        }
        Database.getInstance().getCourseRegistrationRequests().remove(student);
        System.out.println("Done!");
    }
}