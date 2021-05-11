package wsp.models;

import wsp.exceptions.IllegalOperationException;
import wsp.interfaces.CanAlterUserData;
import java.io.Serializable;
import java.util.Objects;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public abstract class User implements Cloneable, Serializable, Comparable<User> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String name;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String surname;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String id;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String login;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String password;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public User() {
		super();
	}

	public User(String name, String surname, String id, String login, String password) {
		this.name = name;
		this.surname = surname;
		this.id = id;
		this.login = login;
		this.password = password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void viewNews() {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getName() {
		// TODO implement me
		return name;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getSurname() {
		// TODO implement me
		return surname;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getId() {
		// TODO implement me
		return id;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getLogin() {
		// TODO implement me
		return login;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String getPassword() {
		// TODO implement me
		return password;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setPassword(User user, String password) throws IllegalOperationException {
		// TODO implement me
		if(user == this || user instanceof CanAlterUserData) {
			this.password = password;
		}
		throw new IllegalOperationException("Illegal operation! Only admins have permission to perform such operations.");
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return (name.equals(user.name) && surname.equals(user.surname)
				&& id.equals(user.id) && login.equals(user.login)
				&& password.equals(user.password)
		);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	@Override
	public int hashCode() {
		return Objects.hash(name, surname, id, login, password);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Object clone() throws CloneNotSupportedException {
		User user = (User) super.clone();
		user.name = name;
		user.surname = surname;
		user.id = id;
		user.login = login;
		user.password = password;
		return user;
	}

	@Override
	public int compareTo(User o) {
		return 0;
	}
}