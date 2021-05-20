package wsp.database;

import wsp.enums.FacultyName;
import wsp.models.*;
import java.io.*;
import java.util.*;

/**
 * Main Database for the WSP University System. Contains all the data of users, courses, faculties,
 * specialties, messages of employees, performance reports, log actions and news. Provides the ability
 * to retrieve, add and update data.
 */
public class Database implements Serializable {
	/**
	 * This class implements Singleton Design Pattern. Thus it posses private constructor with no ability
	 * to create new instances of this class. Global access is provided. All functionality is provided
	 * via instance field. Usage: {@code Database.getInstance()}, then get the needed function.
	 */
	private static Database instance;
	
	/**
	 * This field contains all logins and passwords of users. They are grouped by the type of the user.
	 * Like {@code loginsAndPasswords.get("student")} will return all logins and passwords of students.
	 */
	private HashMap<String, HashMap<String, String>> loginsAndPasswords;
	
	/**
	 * This field contains all users that are registered to the system.
	 */
	private HashSet<User> users;
	
	/**
	 * This field contains all courses. All of them are combined in one {@code HashSet<>} independent
	 * from faculty.
	 */
	private HashSet<Course> courses;
	
	/**
	 * All faculties of the System lay there.
	 */
	private HashSet<Faculty> faculties;
	
	/**
	 * User logging actions are contained in this field as list of strings.
	 */
	private ArrayList<String> userActions;
	
	/**
	 * Messaging between employees, often between manager and teacher are contained there.
	 * Can be accessed for further manipulations.
	 */
	private ArrayList<Message> messages;
	
	/**
	 * Contains statistical reports about students performance. Is made by manager.
	 */
	private ArrayList<String> reports;

	/**
	 * Contains all news in the system. They can be viewed by students and teachers. Manager
	 * manages these news by adding, deleting or updating them.
	 */
	private ArrayList<News> allNews;
	
	/**
	 * Private constructor of the Singleton class. Can't be more than 1 object. Initializes base fields
	 * for casual functioning of the system.
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

		addUser(new Admin("Admin", "Admin", "MAIN1ADM", "admin@kbtu.kz", "admin", 600000));
	}

	/**
	 * Retrieves the singleton instance of this Database class. Via this method all functionality
	 * of the system is performed.
	 *
	 * @return static instance of the Database
	 */
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	/**
	 * Gets all users registered in the system.
	 *
	 * @return all registered users
	 */
	public HashSet<User> getUsers() {
		return users;
	}

	/**
	 * Gets all employees registered in the system.
	 *
	 * @return all registered employees
	 */
	public HashSet<Employee> getEmployees() {
		HashSet<Employee> employees = new HashSet<>();

		for(User user : users) {
			if(user instanceof Employee) {
				employees.add((Employee) user);
			}
		}
		return employees;
	}

	/**
	 * Gets all users registered in the system except the given one.
	 *
	 * @param exception user to exclude from general set
	 * @return users set with one excluded user
	 */
	public HashSet<User> getUsersExcept(User exception) {
		HashSet<User> usersExcept = new HashSet<>();

		for(User user : users) {
			if(user != exception) {
				usersExcept.add(user);
			}
		}
		return usersExcept;
	}
	
	/**
	 * Adds the given user to Database. Firstly added to users set, then to corresponding section
	 * of {@code loginsAndPasswords}.
	 *
	 * @param user user to add to Database
	 */
	public void addUser(User user) {
		users.add(user);
		loginsAndPasswords.get(user.getClass().getSimpleName().toLowerCase()).put(
				user.getLogin(), user.getPassword()
		);
	}

	/**
	 * Removes the given user from Database. Firstly removed from users set, then from corresponding section
	 * of {@code loginsAndPasswords}.
	 *
	 * @param user user to remove from Database
	 */
	public void removeUser(User user) {
		users.remove(user);
		loginsAndPasswords.get(user.getClass().getSimpleName().toLowerCase()).remove(
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
	public ArrayList<Message> getMessagesOf(Employee employee) {
		ArrayList<Message> messagesOf = new ArrayList<>();

		for(Message message : messages) {
			if(message.toWho() == employee) {
				messagesOf.add(message);
			}
		}
		return messagesOf;
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

	/**
	 * Saves (serializes) the current Database state to separate file named {@code db.out}.
	 *
	 * @throws IOException when some inner processes are interrupted or corrupted
	 */
	public static void save() throws IOException {
		try {
			FileOutputStream file = new FileOutputStream("db.out");
			ObjectOutputStream obj = new ObjectOutputStream(file);

			obj.writeObject(instance);
			obj.close();
			file.close();
		} catch(IOException exc) {
			throw new IOException("Couldn't save the current progress");
		}
	}

	/**
	 * Loads (deserializes) previously saved state of the Database. Is assigned to {@code instance}
	 * itself, not creating auxiliary instances of Database.
	 *
	 * @throws IOException when some inner processes are interrupted or corrupted
	 */
	public static void load() throws IOException {
		try {
			FileInputStream file = new FileInputStream("db.out");
			ObjectInputStream obj = new ObjectInputStream(file);

			instance = (Database) obj.readObject();
			obj.close();
			file.close();
		} catch(IOException exc) {
			System.out.println(exc.getMessage());
			instance = new Database();
		} catch(ClassNotFoundException exc) {
			throw new IOException(("Failed to load the progress, class not found"));
		}
	}
}