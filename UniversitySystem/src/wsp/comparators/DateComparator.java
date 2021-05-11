package wsp.comparators;

import wsp.models.Message;
import java.util.Comparator;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class DateComparator implements Comparator<Message> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public DateComparator() {
		super();
	}

	@Override
	public int compare(Message m1, Message m2) {
		return m1.getPostDate().compareTo(m2.getPostDate());
	}
}