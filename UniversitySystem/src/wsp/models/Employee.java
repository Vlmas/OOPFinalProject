package wsp.models;

import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public abstract class Employee extends User {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private double salary;
	
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
	 */
	public Employee() {
		super();
	}

	public Employee(String name, String surname, String id, String login, String password,
					double salary, ArrayList<Message> messages) {

		super(name, surname, id, login, password);
		this.salary = salary;
		this.messages = messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public abstract void sendMessage();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String readMessages() {
		// TODO implement me
		return messages.toString();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public double getSalary() {
		// TODO implement me
		return salary;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setSalary(double salary) {
		// TODO implement me

	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Object clone() throws CloneNotSupportedException {
		Employee employee = (Employee) super.clone();
		employee.salary = salary;
		employee.messages = new ArrayList<>();
		return employee;
	}

	@Override
	public int compareTo(User o) {
		Employee employee = (Employee) o;
		if(super.compareTo(employee) == 0) {
			if(salary == employee.salary) {
				return ((salary > employee.salary) ? 1 : -1);
			}
			return 0;
		}
		return super.compareTo(employee);
	}
}