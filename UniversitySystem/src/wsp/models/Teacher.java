package wsp.models;

import wsp.enums.TeacherTitle;
import wsp.exceptions.IllegalOperationException;
import wsp.interfaces.CanAlterCourseData;
import wsp.interfaces.CanRateTeacher;

import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Teacher extends Employee implements CanAlterCourseData {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private TeacherTitle title;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private int experience;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<Course> courses;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private double rating;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Teacher() {
		super();
	}

	public Teacher(String name, String surname, String id, String login, String password, double salary,
				   ArrayList<Message> messages, TeacherTitle title,
				   int experience, ArrayList<Course> courses, double rating) {

		super(name, surname, id, login, password, salary, messages);
		this.title = title;
		this.experience = experience;
		this.courses = courses;
		this.rating = rating;
	}

	@Override
	public void sendMessage() {

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public TeacherTitle getTitle() {
		// TODO implement me
		return title;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public int getExperience() {
		// TODO implement me
		return experience;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Course> getCourses() {
		// TODO implement me
		return courses;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public double getRating() {
		// TODO implement me
		return rating;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setCourse(Course course) {
		// TODO implement me

	}

	public void setRating(User user, double rating) throws IllegalOperationException {
		int count = 0x000F; //TODO temporary
		if(user instanceof CanRateTeacher) {
			this.rating += (rating - this.rating) / count;
		} else {
			throw new IllegalOperationException("Only students can rate teachers!");
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void putMark(Student student, Course course, Mark mark) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addCourseFile(Course course, CourseFile courseFile) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void deleteCourseFile(Course course) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void changeMark(Student student, Course course, Mark mark) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Course getCourse(String name) {
		// TODO implement me
		return null;	
	}
}