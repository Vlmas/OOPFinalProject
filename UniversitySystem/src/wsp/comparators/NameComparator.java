package wsp.comparators;

import wsp.models.User;
import java.util.Comparator;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class NameComparator implements Comparator<User> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public NameComparator() {
		super();
	}

	@Override
	public int compare(User u1, User u2) {
		return u1.getName().compareTo(u2.getName());
	}
}