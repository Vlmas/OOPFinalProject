package wsp.comparators;

import wsp.models.Message;
import java.util.Comparator;

public class DateComparator implements Comparator<Message> {
	public DateComparator() {
		super();
	}

	@Override
	public int compare(Message m1, Message m2) {
		return m1.getPostDate().compareTo(m2.getPostDate());
	}
}