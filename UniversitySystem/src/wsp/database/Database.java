package wsp.database;

import wsp.models.*;
import java.io.Serializable;
import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Database implements Serializable {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private static Database instance;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private HashMap<String, HashMap<String, String>> loginsAndPasswords;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private HashSet<User> users;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private HashSet<Course> courses;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private HashSet<Faculty> faculties;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<String> userActions;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<Message> messages;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private ArrayList<String> reports;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private ArrayList<News> allNews;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	private Database() {
		loginsAndPasswords = new HashMap<>();
		users = new HashSet<>();
		courses = new HashSet<>();
		faculties = new HashSet<>();
		userActions = new ArrayList<>();
		messages = new ArrayList<>();
		reports = new ArrayList<>();
		allNews = new ArrayList<>();

		loginsAndPasswords.put("admin", new HashMap<>());
		loginsAndPasswords.put("manager", new HashMap<>());
		loginsAndPasswords.put("teacher", new HashMap<>());
		loginsAndPasswords.put("librarian", new HashMap<>());
		loginsAndPasswords.put("student", new HashMap<>());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<User> viewUsers() {
		return users;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addUser(User user) {
		users.add(user);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Student> getStudents() {
		HashSet<Student> students = new HashSet<>();

		for(User user : users) {
			if(user instanceof Student) {
				students.add((Student) user);
			}
		}
		return students;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Teacher> getTeachers() {
		HashSet<Teacher> teachers = new HashSet<>();

		for(User user : users) {
			if(user instanceof Teacher) {
				teachers.add((Teacher) user);
			}
		}
		return teachers;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Manager> getManagers() {
		HashSet<Manager> managers = new HashSet<>();

		for(User user : users) {
			if(user instanceof Manager) {
				managers.add((Manager) user);
			}
		}
		return managers;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Admin> getAdmins() {
		HashSet<Admin> admins = new HashSet<>();

		for(User user : users) {
			if(user instanceof Admin) {
				admins.add((Admin) user);
			}
		}
		return admins;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Librarian> getLibrarians() {
		HashSet<Librarian> librarians = new HashSet<>();

		for(User user : users) {
			if(user instanceof Librarian) {
				librarians.add((Librarian) user);
			}
		}
		return librarians;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Course> getCourses() {
		return courses;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<Faculty> getFaculties() {
		return faculties;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<String> getUserActions() {
		return userActions;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<String> getReports() {
		return reports;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<String> getUserLoginAndPassword(User user) {
		// TODO implement me
		return null;	
	}

	public HashMap<String , String> getUserLoginsAndPasswords(String user) {
		return loginsAndPasswords.get(user);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setUserLoginAndPassword(User user, String login, String password) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addMessage(Message message) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addReport(String report) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addUserAction(String action) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addFaculty(Faculty faculty) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addCourse(Course course) {
		// TODO implement me

	}
}