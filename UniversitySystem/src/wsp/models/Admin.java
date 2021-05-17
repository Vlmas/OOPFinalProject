package wsp.models;

import wsp.database.Database;
import wsp.interfaces.CanAlterUserData;
import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Admin extends Employee implements CanAlterUserData {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Admin() {
		super();
	}

	public Admin(String name, String surname, String id, String login, String password,
				 double salary, ArrayList<Message> messages) {

		super(name, surname, id, login, password, salary, messages);
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
	
	public void addUser(User user) {
		Database.getInstance().addUser(user);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void removeUser(User user) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void updateUser(User user) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<User> viewUsers() {
		// TODO implement me
		return null;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public HashSet<User> getUsers() {
		return Database.getInstance().getUsers();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public ArrayList<String> getUserActions() {
		return Database.getInstance().getUserActions();
	}
}