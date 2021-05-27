package wsp.models;

import wsp.enums.FacultyName;
import java.io.Serializable;
import java.util.*;

public class Faculty implements Serializable {
	private FacultyName name;
	private ArrayList<Specialty> specialties;

	public Faculty() {}

	public Faculty(FacultyName name, ArrayList<Specialty> specialties) {
		this.name = name;
		this.specialties = specialties;
	}

	public FacultyName getName() {
		return name;
	}
	
	public ArrayList<Specialty> getSpecialties() {
		return specialties;
	}

	@Override
	public String toString() {
		return name.toString();
	}
}