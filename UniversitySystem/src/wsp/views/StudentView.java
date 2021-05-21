package wsp.views;

import wsp.database.Database;
import wsp.models.*;
import wsp.utils.GlobalReader;
import wsp.utils.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;

public class StudentView extends UserView {
    private Student student;

    public StudentView() {
        setMenu(
                "--------------------------\n" +
                "|1| View courses\n" +
                "|2| Register for a course\n" +
                "|3| View transcript\n" +
                "|4| Rate teacher\n" +
                "|5| Download transcript\n" +
                "|X| Cancel\n" +
                "--------------------------"
        );
    }

    public StudentView(Student student) {
        this();
        this.student = student;
        greet();
    }

    @Override
    public boolean performAction(String choice) throws IOException, InterruptedException {
        switch(choice.toLowerCase()) {
            case "1" -> viewCourses();
            case "2" -> registerForCourse();
            case "3" -> viewTranscript();
            case "4" -> rateTeacher();
            case "5" -> downloadTranscript();
            case "6", "x", "q", "exit", "quit" -> {
                System.out.println("Logging out.. Goodbye, " + student.getName() + "!");
                return false;
            }
            default -> {
                System.out.println("Wrong operation was selected, please, choose the correct one");
                return true;
            }
        }
        return true;
    }

    @Override
    public void greet() {
        System.out.println("Welcome, " + student.getName() + "!");
        Database.getInstance().addUserAction("User " + student.getName() + " (Student) logged in at " + new Date().toString());
    }

    public void viewCourses() throws IOException, InterruptedException {
        System.out.println("\nList of all courses: ");

        ArrayList<Course> courses = new ArrayList<>(Database.getInstance().getCourses());
        int index = 1;

        for(Course course : courses) {
            if(student.getCourses().contains(course)) {
                System.out.println((index) + ") " + course + Util.COLOR_BLUE + " (Registered)" + Util.COLOR_RESET);
            } else {
                System.out.println((index) + ") " + course);
            }
            index++;
        }

        System.out.println("\nChoose a course to view some of its details, or X to skip");
        String chosen = GlobalReader.reader.readLine();
        if(chosen.equalsIgnoreCase("X")) {
            System.out.println("Operation was skipped");
            return;
        }

        int choice = Util.parseChoice(chosen);
        if(choice < 0) {
            System.out.println("Out of range of courses list");
            return;
        }
        if(Util.isInRange(choice, 0, courses.size() - 1)) {
            viewCourseDetails(courses.get(choice));
            Thread.sleep(500);
            viewCourses();
        } else {
            System.out.println("Out of range of courses list");
        }
    }

    public void registerForCourse() {}

    public void viewTranscript() {}

    public void rateTeacher() {}

    public void downloadTranscript() {}

    public void viewCourseDetails(Course course) throws IOException {
        System.out.println("Course: " + course);
        System.out.println("|1| View course description\n|2| View course details\n|3| View course files\n" +
                "|4| View course teachers");

        switch(GlobalReader.reader.readLine().toLowerCase()) {
            case "1" -> viewCourseDescription(course);
            case "2" -> viewCourseNotion(course);
            case "3" -> viewCourseFiles(course);
            case "4" -> viewCourseTeachers(course);
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

    public void viewCourseFiles(Course course) {
        ArrayList<CourseFile> files = course.getCourseFiles();

        if(!files.isEmpty()) {
            for(CourseFile file : files) {
                System.out.println(file);
            }
        } else {
            System.out.println("No files for this course yet");
        }
    }

    public void viewCourseTeachers(Course course) {
        HashSet<Teacher> teachers = Database.getInstance().getCourseTeachers(course);

        if(!teachers.isEmpty()) {
            int index = 1;
            for(Teacher teacher : teachers) {
                System.out.println(index + ") " + teacher.getName() + " " + teacher.getSurname()
                    + ", " + teacher.getTitle() + ", " + teacher.getExperience() + " years of experience");
                index++;
            }
        } else {
            System.out.println("This course doesn't have any instructor yet");
        }
    }
}