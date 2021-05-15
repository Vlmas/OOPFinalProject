package wsp.models;

import wsp.enums.LessonType;
import wsp.exceptions.IllegalOperationException;
import wsp.interfaces.CanAlterCourseData;
import java.io.Serializable;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Lesson implements Serializable {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private LessonType type;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Date time;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Lesson() {
		super();
	}

	public Lesson(LessonType type, Date time) {
		this.type = type;
		this.time = time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public LessonType getType() {
		return type;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Date getTime() {
		return time;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void setTime(User user, Date time) throws IllegalOperationException {
		if(user instanceof CanAlterCourseData) {
			this.time = time;
		} else {
			throw new IllegalOperationException("Only teacher or manager can change lessons!");
		}
	}
}