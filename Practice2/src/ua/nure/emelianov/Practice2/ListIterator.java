package ua.nure.emelianov.Practice2;

import java.util.Iterator;

interface ListIterator extends Iterator<Object> {
	boolean hasPrevious();

	Object previous();

	void set(Object e);

	void remove();
}
