package wsp.models;

import java.io.Serializable;
import java.util.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class Transcript implements Serializable {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private TreeMap<Period, HashMap<Course, Mark>> marksOfCourses;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private double overallGpa;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Transcript() {
		marksOfCourses = new TreeMap<>();
		overallGpa = 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public double determineOverallGpa() {
		int credits = 0;
		double products = 0;

		for(Map.Entry<Period, HashMap<Course, Mark>> entry : marksOfCourses.entrySet()) {
			for(Map.Entry<Course, Mark> courseMark : entry.getValue().entrySet()) {
				credits += courseMark.getKey().getCreditsAmount();
				products += (courseMark.getKey().getCreditsAmount() * courseMark.getValue().getGpa());
			}
		}
		overallGpa = (products / credits);

		return overallGpa;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public HashMap<Course, Mark> getMarksOfCourses() {
		return null;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Mark getMarkOfCourse(Course parameter) {
		// TODO implement me
		return null;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public double getOverallGpa() {
		return overallGpa;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public String viewMarks() {
		return "";	
	}

	public TreeMap<Period, HashMap<Course, Mark>> getMarks() {
		return marksOfCourses;
	}
}