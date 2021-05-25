package wsp.models;

import wsp.enums.*;
import wsp.interfaces.CanRateTeacher;

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
	public Student(String name, String surname, String id, String login, String password, YearOfStudy yearOfStudy,
				   Degree degree, Faculty faculty, Specialty specialty, Transcript transcript, double gpa) {

		super(name, surname, id, login, password);
		this.yearOfStudy = yearOfStudy;
		this.degree = degree;
		this.faculty = faculty;
		this.specialty = specialty;
		this.transcript = transcript;
		this.gpa = gpa;
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

	/**
	 * String representation of the student.
	 *
	 * @return student as string
	 */
	@Override
	public String toString() {
		return (super.toString() + ". Year of study: " + yearOfStudy + ". Degree: " + degree
		+ ". Faculty: " + faculty.getName() + ". Specialty: " + specialty.getName() + ". GPA: " + gpa);
	}
}