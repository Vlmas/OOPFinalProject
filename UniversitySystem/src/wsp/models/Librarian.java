package wsp.models;

import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Librarian extends Employee {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private HashMap<Book, Integer> library;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Librarian() {
		super();
	}

	public Librarian(String name, String surname, String id, String login, String password,
					 double salary, ArrayList<Message> messages, HashMap<Book, Integer> library) {
		super(name, surname, id, login, password, salary, messages);
		this.library = library;
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
	
	public Book giveBook(Book book) {
		// TODO implement me
		return null;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void orderBook() {
		// TODO implement me

	}
}