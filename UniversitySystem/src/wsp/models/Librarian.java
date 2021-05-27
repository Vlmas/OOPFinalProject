package wsp.models;

import java.util.*;

/**
 * WSP University System's Librarian model. Has a library (University property), can order books and view the library.
 *
 * @see Book
 */
public class Librarian extends Employee {
	/**
	 * Represents the library, where key is a book, and value is amount of books of this kind left in the library.
	 */
	private HashMap<Book, Integer> library;

	public Librarian() {}

	/**
	 * Creates the librarian with base User and Employee fields, then specific {@code HashMap} field for the library.
	 */
	public Librarian(String name, String surname, String id, String login, String password,
					 double salary, HashMap<Book, Integer> library) {
		super(name, surname, id, login, password, salary);
		this.library = library;
	}

	/**
	 * Retrieves the library.
	 *
	 * @return library as map of books and integer values representing quantity
	 */
	public HashMap<Book, Integer> getLibrary() {
		return library;
	}
}