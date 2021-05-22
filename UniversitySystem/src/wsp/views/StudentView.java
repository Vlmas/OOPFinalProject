package wsp.views;

import wsp.database.Database;
import wsp.enums.AttestationSeason;
import wsp.enums.FacultyName;
import wsp.models.*;
import wsp.utils.GlobalReader;
import wsp.utils.Util;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class StudentView extends UserView {
    private Student student;

    public StudentView() {
        setMenu(
                "--------------------------\n" +
                "|1| View personal data\n" +
                "|2| View courses\n" +
                "|3| Register for a course\n" +
                "|4| View transcript\n" +
                "|5| Rate teacher\n" +
                "|6| Download transcript\n" +
                "|7| View news\n" +
                "|X| Logout\n" +
                "--------------------------"
        );
    }

    public StudentView(Student student) {
        this();
        this.student = student;
        this.student.getCourses().add((Course) Database.getInstance().getCoursesOf(FacultyName.FIT).toArray()[0]);
        this.student.getCourses().add((Course) Database.getInstance().getCoursesOf(FacultyName.SECMC).toArray()[0]);
        Period period1 = new Period(2021, AttestationSeason.FALL);
        this.student.getTranscript().getMarks().put(period1, new HashMap<>());
        this.student.getTranscript().getMarks().get(period1).put(student.getCourses().get(0), new Mark(28, 28, 39));
        greet();
    }

    @Override
    public boolean performAction(String choice) throws IOException, InterruptedException {
        switch(choice.toLowerCase()) {
            case "1" -> viewPersonalData();
            case "2" -> viewCourses();
            case "3" -> registerForCourse();
            case "4" -> viewTranscript();
            case "5" -> rateTeacher();
            case "6" -> downloadTranscript();
            case "7" -> viewNews();
            case "8", "x", "q", "exit", "quit" -> {
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

    public void viewPersonalData() throws InterruptedException {
        System.out.println("Student: |" + student.getId() + "| " + student.getName() + " " + student.getSurname());
        System.out.println("Year of study: " + student.getYearOfStudy() + "\nDegree: " + student.getDegree());
        System.out.println("Faculty: " + student.getFaculty().getName() + "\nSpecialty: " + student.getSpecialty().getName() + "\nGPA: " + student.getGpa());
        Thread.sleep(1000);
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

    public void registerForCourse() throws IOException, InterruptedException {
        int creditsSum = student.getCourses().stream().mapToInt(Course::getCreditsAmount).sum();
        ArrayList<Course> courses = new ArrayList<>(Database.getInstance().getCoursesOf(student.getFaculty().getName()));
        courses.addAll(Database.getInstance().getCoursesOf(FacultyName.GEF));

        System.out.println("Total taken credits: " + creditsSum + ". Can't be more than 21");
        System.out.println("List of courses available for registration: ");

        for(int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getFaculty() == FacultyName.GEF) {
                System.out.println((i + 1) + ") " + Util.COLOR_BLUE + "(Elective) " + Util.COLOR_RESET + courses.get(i) + ". Credits: " + courses.get(i).getCreditsAmount());
            } else {
                System.out.println((i + 1) + ") " + courses.get(i) + ". Credits: " + courses.get(i).getCreditsAmount());
            }
        }

        System.out.print("\nSelect the course to register: ");
        int choice = Util.parseChoice(GlobalReader.reader.readLine());
        if(choice < 0) {
            System.out.println("Invalid input");
            return;
        }
        if(Util.isInRange(choice, 0, courses.size() - 1)) {
            if(student.getCourses().contains(courses.get(choice))) {
                System.out.println("Course is already registered");
                return;
            }
            if(courses.get(choice).getCreditsAmount() + creditsSum > 21) {
                System.out.println("Impossible to register, limit of credits exceeded");
                return;
            }
            Database.getInstance().addCourseRegistrationRequest(student, courses.get(choice));
            System.out.println("Your request has been accepted! Waiting for manager's approval..");
            System.out.println(Database.getInstance().getCourseRegistrationRequests());
            Thread.sleep(500);
        } else {
            System.out.println("Unexpected input");
        }
    }

    public void viewTranscript() {
        System.out.println("Your overall GPA: " + student.getTranscript().determineOverallGpa());
        System.out.println("Course; First attestation; Second attestation; Final points; Overall; Course GPA");
        for(Map.Entry<Period, HashMap<Course, Mark>> entry : student.getTranscript().getMarks().entrySet()) {
            System.out.println(entry.getKey() + ":");
            int index = 1;
            for(Map.Entry<Course, Mark> courseMark : entry.getValue().entrySet()) {
                System.out.println(index + ") " + courseMark.getKey() + ": " + courseMark.getValue());
                index++;
            }
            System.out.println();
        }
    }

    public void rateTeacher() throws IOException {
        ArrayList<Course> courses = student.getCourses();
        System.out.println("Your courses: ");

        for(int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ") " + courses.get(i));
        }
        System.out.print("\nWhich course teachers you wish to rate: ");
        int choice = Util.parseChoice(GlobalReader.reader.readLine());

        ArrayList<Teacher> teachers = new ArrayList<>(Database.getInstance().getCourseTeachers(courses.get(choice)));
        if(teachers.isEmpty()) {
            System.out.println("No instructors for this course yet");
            return;
        }
        viewCourseTeachers(courses.get(choice));

        System.out.print("Now select the teacher: ");
        choice = Util.parseChoice(GlobalReader.reader.readLine());
        System.out.print("Enter your rating (10 points scale): ");
        double rating = Double.parseDouble(GlobalReader.reader.readLine());
        try {
            teachers.get(choice).setRating(rating);
        } catch(IndexOutOfBoundsException exc) {
            System.out.println("Out of range");
            return;
        }
        System.out.println("Thanks for your rating!");
    }

    public void downloadTranscript() {
        try {
            String fileName = "transcript_" + student.getName().toLowerCase() + ".txt";
            PrintWriter writer = new PrintWriter(new FileWriter(fileName, true));
            for(Map.Entry<Period, HashMap<Course, Mark>> entry : student.getTranscript().getMarks().entrySet()) {
                writer.write(entry.getKey() + ":\n");
                int index = 1;
                for(Map.Entry<Course, Mark> courseMark : entry.getValue().entrySet()) {
                    writer.write(index + ") " + courseMark.getKey() + ": " + courseMark.getValue() + "\n");
                    index++;
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Transcript was successfully downloaded!");
        } catch(IOException exc) {
            System.out.println("Failed to download the transcript.. Error message: " + exc.getMessage());
        }
    }

    public void viewNews() {
        Util.viewNews();
    }

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
                    + ", " + teacher.getTitle() + ", " + teacher.getExperience()
                    + " years of experience, rating: " + teacher.getRating());
                index++;
            }
        } else {
            System.out.println("This course doesn't have any instructor yet");
        }
    }
}