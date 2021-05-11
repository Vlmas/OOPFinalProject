package wsp.comparators;

import wsp.models.User;
import java.util.Comparator;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class IdComparator implements Comparator<User> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public IdComparator() {
		super();
	}

	@Override
	public int compare(User u1, User u2) {
		return u1.getId().compareTo(u2.getId());
	}
}