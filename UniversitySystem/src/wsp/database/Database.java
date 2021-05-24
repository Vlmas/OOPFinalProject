package wsp.database;

import wsp.enums.*;
import wsp.models.*;
import wsp.utils.Util;
import java.io.*;
import java.time.DayOfWeek;
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
	 * Contains all requests from students for course registration. Is viewed by manager, who later
	 * decides to accept or reject this request, despite its correctness.
	 */
	private HashMap<Student, ArrayList<Course>> courseRegistrationRequests;
	
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
		courseRegistrationRequests = new HashMap<>();

		initializeLoginPasswords();
		initializeFaculties();
		initializeCourses();
		initializeUsers();
		addNews(new News("WSP started its work!", "Great times are ahead, enjoy!!", new ArrayList<>() {{ add(Util.COLOR_WHITE + "That's very cool." + Util.COLOR_RESET); add(Util.COLOR_WHITE + "Nice, I'm happy." + Util.COLOR_RESET); }}, new Date()));
	}

	private void initializeLoginPasswords() {
		loginsAndPasswords.putIfAbsent("admin", new HashMap<>());
		loginsAndPasswords.putIfAbsent("manager", new HashMap<>());
		loginsAndPasswords.putIfAbsent("teacher", new HashMap<>());
		loginsAndPasswords.putIfAbsent("librarian", new HashMap<>());
		loginsAndPasswords.putIfAbsent("student", new HashMap<>());
	}

	private void initializeFaculties() {
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
	}

	private void initializeUsers() {
		addUser(new Admin("Admin", "Admin", "MAIN1ADM", "admin@kbtu.kz", "admin", 500000));
		addUser(new Teacher("Pakita", "Shamoi", "TCH", "p_shamoi@kbtu.kz", "Pakita", 900000, TeacherTitle.PROFESSOR, 10, new ArrayList<>() {{ add((Course) courses.toArray()[0]); }}, 0));
		addUser(new Manager("Nazym", "Aidarkhanova", "MAIN1MNG", "n_aidarkhanova@kbtu.kz", "Nazym", 450000, ManagerType.OR));
		addUser(new Student("Almas", "Alemarov", "19B030614", "a_alemarov@kbtu.kz", "AAA", YearOfStudy.SECOND, Degree.BACHELOR, getFaculty(FacultyName.FIT), getFaculty(FacultyName.FIT).getSpecialties().get(0), new ArrayList<>(), new Transcript(), 0));
		addUser(new Librarian("Lib", "Lib", "LIB", "libr@kbtu.kz", "Book", 400000, new HashMap<>()));
	}

	private void initializeCourses() {
		addCourse(
			new Course("Object-Oriented Programming",
				"Object Technology has been in development for over forty years. It is now embedded in such diverse areas requirements engineering...",
				"CSCI2106",
				FacultyName.FIT,
				3,
				new ArrayList<>(),
				new ArrayList<>() {{
					add(new Lesson(LessonType.LECTURE, DayOfWeek.TUESDAY, 14, 0, 15, 0));
					add(new Lesson(LessonType.LECTURE, DayOfWeek.TUESDAY, 15, 0, 16, 0));
					add(new Lesson(LessonType.PRACTICE, DayOfWeek.TUESDAY, 16, 0, 17, 0));
					add(new Lesson(LessonType.PRACTICE, DayOfWeek.TUESDAY, 17, 0, 18, 0));
				}}
			)
		);
		addCourse(
			new Course("Physics II",
				"Physics 2 is the second part of the physics course including basic topics of General Physics...",
				"PHYS2102",
				FacultyName.GEF,
				3,
				new ArrayList<>(),
				new ArrayList<>() {{
					add(new Lesson(LessonType.LECTURE, DayOfWeek.MONDAY, 14, 0, 15, 0));
					add(new Lesson(LessonType.PRACTICE, DayOfWeek.THURSDAY, 12, 0, 13, 0));
					add(new Lesson(LessonType.LAB, DayOfWeek.THURSDAY, 16, 0, 18, 0));
				}}
			)
		);
		addCourse(
			new Course("Discrete Mathematics",
				"Discrete mathematics is the study of mathematical structures that are countable or otherwise distinct and separable...",
				"CSCI1102",
				FacultyName.SECMC,
				3,
				new ArrayList<>(),
				new ArrayList<>() {{
					add(new Lesson(LessonType.LECTURE, DayOfWeek.TUESDAY, 14, 0, 15, 0));
					add(new Lesson(LessonType.LECTURE, DayOfWeek.TUESDAY, 15, 0, 16, 0));
					add(new Lesson(LessonType.PRACTICE, DayOfWeek.TUESDAY, 16, 0, 17, 0));
				}}
			)
		);
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
	 * Gets all users that are students.
	 *
	 * @return set of students
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
	 * Gets all users that are teachers.
	 *
	 * @return set of teachers
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
	 * Gets teachers of specific course.
	 *
	 * @param course course which teachers to retrieve
	 * @return set of teachers
	 */
	public HashSet<Teacher> getCourseTeachers(Course course) {
		HashSet<Teacher> teachers = new HashSet<>();

		for(User user : users) {
			if(user instanceof Teacher) {
				if(((Teacher) user).getCourses().contains(course)) {
					teachers.add((Teacher) user);
				}
			}
		}
		return teachers;
	}
	
	/**
	 * Gets all users that are managers.
	 *
	 * @return set of managers
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
	 * Gets all users that are admins.
	 *
	 * @return set of admins
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
	 * Gets all users that are librarians.
	 *
	 * @return set of librarians
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
	 * Gets all of the courses independent of faculty from Database.
	 *
	 * @return set of courses
	 */
	public HashSet<Course> getCourses() {
		return courses;
	}

	/**
	 * Gets all courses that are under specific faculty.
	 *
	 * @param facultyName which faculty courses to retrieve
	 * @return set of courses of provided faculty
	 */
	public HashSet<Course> getCoursesOf(FacultyName facultyName) {
		HashSet<Course> resultantCourses = new HashSet<>();

		for(Course course : courses) {
			if(course.getFaculty() == facultyName) {
				resultantCourses.add(course);
			}
		}
		return resultantCourses;
	}

	/**
	 * Gets specific faculty by name.
	 *
	 * @param name name of the faculty
	 * @return faculty
	 */
	public Faculty getFaculty(FacultyName name) {
		for(Faculty faculty : faculties) {
			if(faculty.getName() == name) {
				return faculty;
			}
		}
		return null;
	}
	
	/**
	 * Retrieves all user log actions.
	 *
	 * @return list of user log actions
	 */
	public ArrayList<String> getUserActions() {
		return userActions;
	}
	
	/**
	 * Gets all messages between employees.
	 *
	 * @return list of employee messages
	 */
	public ArrayList<Message> getMessages() {
		return messages;
	}

	/**
	 * Gets messages of specified employee.
	 *
	 * @param employee who's messages to get
	 * @return list of messages
	 */
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
	 * Gets statistical reports made by a manager.
	 *
	 * @return list of statistical reports
	 */
	public ArrayList<String> getReports() {
		return reports;
	}

	/**
	 * Retrieves the user by given login and password.
	 *
	 * @param login login of the user
	 * @param password password of the user
	 * @return retrieved user
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

	/**
	 * Gets all logins and passwords of provided user type.
	 *
	 * @param user which type of users' credentials to get
	 * @return map of credentials
	 */
	public HashMap<String, String> getUserLoginsAndPasswords(String user) {
		return loginsAndPasswords.get(user);
	}

	/**
	 * Adds employee message to Database.
	 *
	 * @param message message to add
	 */
	public void addMessage(Message message) {
		messages.add(message);
	}

	/**
	 * Adds the ready state report to Database.
	 *
	 * @param report report to add
	 */
	public void addReport(String report) {
		reports.add(report);
	}

	/**
	 * Adds given user log action to Database.
	 *
	 * @param action action to add
	 */
	public void addUserAction(String action) {
		userActions.add(action);
	}

	/**
	 * Adds given course to Database.
	 *
	 * @param course course to add
	 */
	public void addCourse(Course course) {
		courses.add(course);
	}

	/**
	 * Retrieves all requests for registration.
	 *
	 * @return map of requests
	 */
	public HashMap<Student, ArrayList<Course>> getCourseRegistrationRequests() {
		return courseRegistrationRequests;
	}

	/**
	 * Adds given request for registration to Database.
	 *
	 * @param student sender of the request
	 * @param course requested course
	 */
	public void addCourseRegistrationRequest(Student student, Course course) {
		courseRegistrationRequests.computeIfAbsent(student, (k) -> new ArrayList<>());
		courseRegistrationRequests.get(student).add(course);
	}

	/**
	 * Returns the list of news in Database. Is managed by a manager.
	 *
	 * @return list of news
	 */
	public ArrayList<News> getNews() {
		return allNews;
	}

	/**
	 * Adds the given news to Database.
	 *
	 * @param news news to add
	 */
	public void addNews(News news) {
		allNews.add(news);
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
			throw new IOException("Couldn't save the current progress." + exc.getMessage() + ":" + exc.getCause());
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
			instance = new Database();
		} catch(ClassNotFoundException exc) {
			throw new IOException(("Failed to load the progress, class not found"));
		}
	}
}