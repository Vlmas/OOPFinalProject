package wsp.models;

import wsp.enums.Grade;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class Mark implements Serializable {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private double firstAttestation;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private double secondAttestation;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private double finalExam;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private double overallDigital;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private Grade overallLetter;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private double gpa;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Mark() {}

	public Mark(double firstAttestation, double secondAttestation, double finalExam) {
		if(firstAttestation > 30 || secondAttestation > 30 || finalExam > 40 || (firstAttestation == secondAttestation && secondAttestation == finalExam && finalExam < 0)) {
			throw new IllegalArgumentException("Illegal values for attestations!");
		}
		this.firstAttestation = firstAttestation;
		this.secondAttestation = secondAttestation;
		this.finalExam = finalExam;
		this.overallDigital = firstAttestation + secondAttestation + finalExam;
		this.overallLetter = determineLetter();
		this.gpa = determineGpa();
	}

	private Grade determineLetter() {
		if(overallDigital <= 100 && overallDigital >= 95) {
			return Grade.A;
		}
		else if(overallDigital >= 90) {
			return Grade.A_MINUS;
		}
		else if(overallDigital >= 85) {
			return Grade.B_PLUS;
		}
		else if(overallDigital >= 80) {
			return Grade.B;
		}
		else if(overallDigital >= 75) {
			return Grade.B_MINUS;
		}
		else if(overallDigital >= 70) {
			return Grade.C_PLUS;
		}
		else if(overallDigital >= 65) {
			return Grade.C;
		}
		else if(overallDigital >= 60) {
			return Grade.C_MINUS;
		}
		else if(overallDigital >= 55) {
			return Grade.D_PLUS;
		}
		else if(overallDigital >= 50) {
			return Grade.D;
		} else {
			return Grade.F;
		}
	}

	private double determineGpa() {
		switch(overallLetter) {
			case A -> { return 4.00; }
			case A_MINUS -> { return 3.67; }
			case B_PLUS -> { return 3.33; }
			case B -> { return 3.00; }
			case B_MINUS -> { return 2.67; }
			case C_PLUS -> { return 2.33; }
			case C -> { return 2.00; }
			case C_MINUS -> { return 1.67; }
			case D_PLUS -> { return 1.33; }
			case D -> { return 1.00; }
			default -> { return 0.00; }
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public double getFirstAttestation() {
		return firstAttestation;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public double getSecondAttestation() {
		return secondAttestation;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public double getFinalExam() {
		return finalExam;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public double getOverallDigital() {
		return overallDigital;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public Grade getOverallLetter() {
		return overallLetter;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public double getGpa() {
		return gpa;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setFirstAttestation(double firstAttestation) {
		this.firstAttestation = firstAttestation;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setSecondAttestation(double secondAttestation) {
		this.secondAttestation = secondAttestation;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	public void setFinalExam(double finalExam) {
		this.finalExam = finalExam;
	}

	@Override
	public String toString() {
		return firstAttestation + "  " + secondAttestation + "  " + finalExam + "  " + overallDigital + "  " + gpa;
	}
}