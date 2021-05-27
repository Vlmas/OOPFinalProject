package wsp.models;

import java.io.Serializable;

public class Specialty implements Serializable {
	private String name;

	public Specialty() {}

	public Specialty(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}