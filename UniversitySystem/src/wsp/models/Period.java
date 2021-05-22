package wsp.models;

import wsp.enums.AttestationSeason;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class Period implements Serializable, Comparable<Period> {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private int year;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	private AttestationSeason season;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Period() {
		super();
	}

	public Period(int year, AttestationSeason season) {
		this.year = year;
		this.season = season;
	}
	
	public int getYear() {
		// TODO implement me
		return year;
	}

	public AttestationSeason getSeason() {
		// TODO implement me
		return season;
	}

	@Override
	public int compareTo(Period o) {
		if(year == o.year) {
			if(season == o.season) {
				return 0;
			}
			return (season == AttestationSeason.SPRING && o.season == AttestationSeason.FALL) ? 1 : -1;
		}
		return (year > o.year) ? 1 : -1;
	}

	@Override
	public String toString() {
		return year + " " + season;
	}
}