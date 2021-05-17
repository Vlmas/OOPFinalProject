package wsp.database;

import wsp.enums.FacultyName;
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

		loginsAndPasswords.putIfAbsent("admin", new HashMap<>());
		loginsAndPasswords.putIfAbsent("manager", new HashMap<>());
		loginsAndPasswords.putIfAbsent("teacher", new HashMap<>());
		loginsAndPasswords.putIfAbsent("librarian", new HashMap<>());
		loginsAndPasswords.putIfAbsent("student", new HashMap<>());

		faculties.add(new Faculty(FacultyName.FIT, new ArrayList<>() {{
			add(new Specialty("Computer Systems and Software"));
			add(new Specialty("Information Systems"));
			add(new Specialty("Automation and Control"));
		}}));
		faculties.add(new Faculty(FacultyName.SECMC, new ArrayList<>() {{
			add(new Specialty("Mathematical and Computer Modeling"));
		}}));
		faculties.add(new Faculty(FacultyName.BS, new ArrayList<>() {{
			add(new Specialty("Management"));
			add(new Specialty("Finance"));
			add(new Specialty("Marketing"));
			add(new Specialty("Accounting"));
		}}));
		faculties.add(new Faculty(FacultyName.FOGI, new ArrayList<>() {{
			add(new Specialty("Oil and Gas Business"));
		}}));
		faculties.add((new Faculty(FacultyName.KMA, new ArrayList<>() {{
			add(new Specialty("Marine Engineering"));
		}})));

		addUser(new Admin("Admin", "Admin", "10BD2367", "admin@kbtu.kz", "admin", 500000, new ArrayList<>()));
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
	
	public HashSet<User> getUsers() {
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
		loginsAndPasswords.get(user.getClass().getSimpleName().toLowerCase()).put(
				user.getLogin(), user.getPassword()
		);
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

	public Faculty getFaculty(FacultyName name) {
		for(Faculty faculty : faculties) {
			if(faculty.getName() == name) {
				return faculty;
			}
		}
		return null;
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
	
	public User getUserByLoginAndPassword(String login, String password) {
		User type = null;

		for(User user : users) {
			if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
				type = user;
			}
		}

		return type;
	}

	public HashMap<String, String> getUserLoginsAndPasswords(String user) {
		return loginsAndPasswords.get(user);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setUserLoginAndPassword(String user, String login, String password) {
		loginsAndPasswords.get(user).put(login, password);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addMessage(Message message) {
		messages.add(message);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addReport(String report) {
		reports.add(report);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addUserAction(String action) {
		userActions.add(action);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addFaculty(Faculty faculty) {
		faculties.add(faculty);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addCourse(Course course) {
		courses.add(course);
	}
}