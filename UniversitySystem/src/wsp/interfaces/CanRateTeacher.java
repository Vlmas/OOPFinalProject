package wsp.interfaces;

import wsp.exceptions.IllegalOperationException;
import wsp.models.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public interface CanRateTeacher {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	void rateTeacher(Teacher teacher, double rating) throws IllegalOperationException;
}