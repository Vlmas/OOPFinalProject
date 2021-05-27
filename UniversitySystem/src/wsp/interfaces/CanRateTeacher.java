package wsp.interfaces;

import wsp.exceptions.IllegalOperationException;
import wsp.models.*;

public interface CanRateTeacher {
	void rateTeacher(Teacher teacher, double rating) throws IllegalOperationException;
}