package wsp.comparators;

import wsp.models.Student;

import java.util.Comparator;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class GpaComparator implements Comparator<Student> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public GpaComparator() {
		super();
	}

	@Override
	public int compare(Student s1, Student s2) {
		if(s1.getGpa() == s2.getGpa()) {
			return 0;
		}
		return ((s1.getGpa() > s2.getGpa()) ? 1 : -1);
	}
}