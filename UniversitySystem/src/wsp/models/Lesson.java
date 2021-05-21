package wsp.models;

import wsp.enums.LessonType;
import wsp.exceptions.IllegalOperationException;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

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
	
	private DayOfWeek day;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private LocalTime startTime;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private LocalTime endTime;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Lesson() {}

	public Lesson(LessonType type, DayOfWeek day, int startHour, int startMinute, int endHour, int endMinute) {
//		if(endHour - startHour > 2) {
//			System.out.println("One lesson duration can't be more than 2 hours!");
//			return;
//		}
		this.type = type;
		this.day = day;
		this.startTime = LocalTime.of(startHour, startMinute);
		this.endTime = LocalTime.of(endHour, endMinute);
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
	
	public LocalTime getStartTime() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public DayOfWeek getDay() {
		return day;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setDayTime(DayOfWeek day, int startHour, int startMinute, int endHour, int endMinute) {
		this.day = day;
		this.startTime = LocalTime.of(startHour, startMinute);
		this.endTime = LocalTime.of(endHour, endMinute);
	}

	@Override
	public String toString() {
		return (
			"|" + type + "| Starts at " + startTime.getHour() + ":" + ((startTime.getMinute() == 0) ? "00" : startTime.getMinute()) + ", ends at "
			+ endTime.getHour() + ":" + ((endTime.getMinute() == 0) ? "00" : endTime.getMinute()) + ". " + day
		);
	}
}