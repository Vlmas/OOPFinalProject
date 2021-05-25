package wsp.views;

import wsp.models.Teacher;
import wsp.database.Database;
import wsp.models.*;
import wsp.utils.Util;
import java.io.IOException;
import java.util.*;

public class TeacherView extends UserView {
    private Teacher teacher;

    public TeacherView() {
        setMenu(
                "--------------------------\n" +
                "|1| View personal data\n"+
                "|2| View courses\n" +
                "|3| Send message\n"+
                "|X| Cancel\n"+
                "--------------------------"
        );
    }

    public TeacherView(Teacher teacher) {
        this();
        this.teacher = teacher;
        greet();
    }

    @Override
    public void greet() {
        System.out.println("Welcome, " + teacher.getTitle() + "!");
        Database.getInstance().addUserAction("User " + teacher.getTitle() + " (Teacher) logged in at " + new Date().toString());
    }

    @Override
    public boolean performAction(String choice) throws IOException, InterruptedException {
        switch(choice.toLowerCase()) {
            case "1" -> viewPersonalData();
            case "2" -> viewCourses();
            case "3" -> sendMessage();
            case "4", "x", "q", "exit", "quit" -> {
                System.out.println("Logging out.. Goodbye, " + teacher.getName() + "!");
                return false;
            }
            default -> {
                System.out.println("Wrong operation was selected, please, choose the correct one");
                return true;
            }
        }
        return true;
    }

    private void viewCourseFile(Course course) throws IOException, InterruptedException {
        System.out.println("|1| Add File\n|2| Delete File");
        switch(Util.reader.readLine().toLowerCase()) {
            case "1" -> addFile(course);
            case "2" -> deleteFile(course);
            default -> System.out.println("Wrong input");
        }
    }

    private void deleteFile(Course course) throws IOException {
        ArrayList<Course> courseFiles = teacher.getCourses();

        if(courseFiles.size() > 0) {
            System.out.println("Select which file to remove, or X to cancel:");

            for(int i = 0; i < courseFiles.size(); i++) {
                System.out.println((i + 1) + ") " + courseFiles.get(i));
            }
            System.out.println("X) Cancel");

            String chosen = Util.reader.readLine();
            if(chosen.equalsIgnoreCase("x")) {
                return;
            }

            int choice = Util.parseChoice(chosen);
            if(choice < 0) {
                System.out.println("Invalid input was entered");
                return;
            }

            if(Util.isInRange(choice, 0, courseFiles.size() - 1)) {
                teacher.deleteCourseFile((Course) courseFiles.get(choice));
                System.out.println("File has been removed!");
            } else {
                System.out.println("Invalid input, not in range of course files");
            }
            return;
        }
        System.out.println("There is currently no files to remove");
    }

    public void viewCourses() throws IOException, InterruptedException {
        System.out.println("\nList of my courses: ");

        ArrayList<Course> courses = new ArrayList<>(teacher.getCourses());
        Util.printList(courses);
        System.out.println("Select a course to manage or X to cancel the request");
        int choice = Util.parseChoice(Util.reader.readLine());
        if(Util.isInRange(choice, 0, courses.size())) {
            manageCourse(courses.get(choice));
        } else {
            System.out.println("Invalid input");
        }
    }

    private void manageCourse(Course course) throws IOException, InterruptedException {
        System.out.println("Course:"+course);
        System.out.println("|1| View students and their marks\n|2| View files\n|3| Course details");
        switch(Util.reader.readLine().toLowerCase()) {
            case "1" -> viewMark();
            case "2" -> viewCourseFile(course);
            case "3" -> viewCourseDetails(course);
            default -> System.out.println("Wrong input");
        }
    }

    private void viewCourseDetails(Course course) throws IOException {
        System.out.println("Course: " + course);
        System.out.println("|1| View course description\n|2| View course details\n");

        switch(Util.reader.readLine().toLowerCase()) {
            case "1" -> viewCourseDescription(course);
            case "2" -> viewCourseNotion(course);
            default -> System.out.println("Wrong input");
        }
    }

    public void viewCourseDescription(Course course) {
        StringTokenizer tokenizer = new StringTokenizer(course.getDescription(), " ");
        StringBuilder description = new StringBuilder(course.getDescription().length());
        int lineLength = 0;
        while(tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            if(lineLength + word.length() > 60) {
                description.append("\n");
                lineLength = 0;
            }
            description.append(word).append(" ");
            lineLength += word.length();
        }
        System.out.println(description.toString());
    }

    public void viewCourseNotion(Course course) {
        System.out.println("Name: " + course.getName());
        System.out.println("Credits amount: " + course.getCreditsAmount());
        System.out.println("Code: " + course.getCode());

        System.out.println("Lessons: ");
        for(Lesson lesson : course.getLessons()) {
            System.out.println(lesson);
        }
    }


    public void viewPersonalData() throws InterruptedException {
        System.out.println("Teacher: |" + teacher.getId() + "| " + teacher.getSurname() +teacher.getName() + teacher.getTitle());
        System.out.println("Experience: " + teacher.getExperience() );
        System.out.println("Rating: " + teacher.getRating());
        Thread.sleep(1000);
    }


    private void viewMark() {

    }

    private void addFile(Course course) throws IOException, InterruptedException {
        System.out.print("File name: ");
        String fileName = Util.reader.readLine();
        CourseFile file = new CourseFile(fileName, new Date());
        course.addCourseFile(file);

//        for(Course course1 : Database.getInstance().getCourses()) {
//            if(course1.equals(course)) {
//                System.out.println("Added to database");
//                course1.addCourseFile(file);
//                System.out.println(course1.getCourseFiles());
//                break;
//            }
//        }
//        for(Student student: Database.getInstance().getStudents()){
//            if(student.getTranscript().getCourses().contains(course)) {
//                System.out.println("Added to student");
//                student.getTranscript().addCourseFile(course, file);
//                break;
//            }
//        }

    }

    private void sendMessage() {
    }
}