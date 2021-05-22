package wsp.models;

import wsp.enums.*;
import wsp.interfaces.CanRateTeacher;
import java.util.*;

/**
 * WSP University System's Student model. Represents the casual student of University.
 * Has his own year of study, degree, faculty and specialty to which he belongs. Can study courses, and
 * {@code Mark} represents his academic performance, which is expressed by attestation points and GPA.
 * This class posses the functionality of viewing news and rating teachers of belonging courses.
 *
 * @see User
 * @see Teacher
 * @see Course
 * @see Mark
 */
public class Student extends User implements CanRateTeacher {
	/**
	 * Represents student year of study.
	 */
	private YearOfStudy yearOfStudy;
	
	/**
	 * Represents student's academic degree.
	 */
	private Degree degree;
	
	/**
	 * The faculty to which student belongs.
	 */
	private Faculty faculty;
	
	/**
	 * The specialty of student, unique and belongs to only one faculty.
	 */
	private Specialty specialty;
	
	/**
	 * Students courses that he registered.
	 */
	private ArrayList<Course> courses;
	
	/**
	 * Student's transcript. Represents the academic performance.
	 */
	private Transcript transcript;
	
	/**
	 * GPA (Grade Point Average) - the measure of student's academic performance.
	 */
	private double gpa;

	public Student() {}

	/**
	 * Creates a student with base user fields and specific student field such as year of study,
	 * degree, etc.
	 */
	public Student(String name, String surname, String id, String login, String password,
				   YearOfStudy yearOfStudy, Degree degree, Faculty faculty, Specialty specialty,
				   ArrayList<Course> courses, Transcript transcript, double gpa) {

		super(name, surname, id, login, password);
		this.yearOfStudy = yearOfStudy;
		this.degree = degree;
		this.faculty = faculty;
		this.specialty = specialty;
		this.courses = courses;
		this.transcript = transcript;
		this.gpa = gpa;
	}

	/**
	 * Adds the given course to students' course list.
	 *
	 * @param course course to add
	 */
	public void addCourse(Course course) {
		courses.add(course);
	}

	public YearOfStudy getYearOfStudy() {
		return yearOfStudy;
	}

	public Degree getDegree() {
		return degree;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public Transcript getTranscript() {
		return transcript;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public void setYearOfStudy(YearOfStudy yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
	/**
	 * Sets the GPA according to Transcript data and returns it.
	 *
	 * @return student GPA
	 */
	public double getGpa() {
		this.gpa = transcript.determineOverallGpa();
		return gpa;
	}
	
	/**
	 * Compares the specified object with this student. Returns {@code true} when both objects are of the
	 * same reference, or when they share the same GPA, year of study, degree, faculty, specialty, courses
	 * and transcript. Otherwise will return {@code false}.
	 */
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		if(!super.equals(o)) {
			return false;
		}
		Student student = (Student) o;
		return (Double.compare(student.gpa, gpa) == 0 && yearOfStudy == student.yearOfStudy
				&& degree == student.degree && Objects.equals(faculty, student.faculty)
				&& Objects.equals(specialty, student.specialty) && Objects.equals(courses, student.courses)
				&& Objects.equals(transcript, student.transcript)
		);
	}

	/**
	 * Returns the hash code value for this object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), yearOfStudy, degree, faculty, specialty, courses, transcript, gpa);
	}

	/**
	 * Sets the rating of a specified teacher. Not sets the exact value, average rating is calculated
	 * inside of the {@code Teacher} class.
	 *
	 * @see Teacher
	 * @param teacher teacher who to rate
	 * @param rating rating to set
	 */
	@Override
	public void rateTeacher(Teacher teacher, double rating) {
		teacher.setRating(rating);
	}
}