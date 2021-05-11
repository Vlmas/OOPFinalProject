package wsp.comparators;

import wsp.models.Employee;
import java.util.Comparator;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class SalaryComparator implements Comparator<Employee> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public SalaryComparator() {
		super();
	}

	@Override
	public int compare(Employee e1, Employee e2) {
		if(e1.getSalary() == e2.getSalary()) {
			return 0;
		}
		return ((e1.getSalary() > e2.getSalary()) ? 1 : -1);
	}
}