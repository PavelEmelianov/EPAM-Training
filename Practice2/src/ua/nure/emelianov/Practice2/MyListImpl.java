package ua.nure.emelianov.Practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {
	private Object[] arr = {};


	@Override
	public void add(Object e) {
		Object[] arr2 = new Object[arr.length + 1];
		System.arraycopy(arr, 0, arr2, 0, arr.length);
		arr2[arr2.length - 1] = e;
		arr = arr2;
	}

	@Override
	public void clear() {
		arr = new Object[] {};
	}

	@Override
	public boolean remove(Object o) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(o)) {
				Object[] arr2 = new Object[arr.length - 1];
				System.arraycopy(arr, 0, arr2, 0, i);
				System.arraycopy(arr, i + 1, arr2, i, arr2.length - i);
				arr = arr2;
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] array = this.arr;

		return array;
	}

	@Override
	public int size() {
		return toArray().length;
	}

	@Override
	public boolean contains(Object o) {

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(MyList c) {
		boolean flag = false;
		for (int j = 0; j < c.toArray().length; j++) {
			flag = false;
			for (int i = 0; i < toArray().length; i++) {
				if (toArray()[i] == c.toArray()[j]) {
					flag = true;

				}
			}
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < toArray().length; i++) {
			if (toArray()[i] == null) {
				sb.append("[" + "null" + "]");
				continue;
			}
			sb.append("[" + toArray()[i].toString() + "]");
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		private boolean flag = true;

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public int getPointer() {
			return pointer;
		}

		public void setPointer(int pointer) {
			this.pointer = pointer;
		}

		public boolean isNextOrPrevious() {
			return nextOrPrevious;
		}

		public void setNextOrPrevious(boolean nextOrPrevious) {
			this.nextOrPrevious = nextOrPrevious;
		}

		private int pointer = -1;
		private boolean nextOrPrevious = false;

		public boolean hasNext() {
			if (pointer < toArray().length-1) {
				return true;
			}
			return false;
		}

		public Object next() {
			flag = false;
			nextOrPrevious = false;
			return toArray()[++pointer];
		}

		public void remove() {
			if (isFlag()) {
				throw new IllegalStateException();
			}
			int i = 0;
			if (!isNextOrPrevious()) {
				i = pointer--;
			} else {
				i = ++pointer;
			}
			Object[] arr2 = new Object[arr.length - 1];
			System.arraycopy(arr, 0, arr2, 0, i);
			System.arraycopy(arr, i + 1, arr2, i, arr2.length - i);
			arr = arr2;
			flag = true;
			if (isNextOrPrevious()) {
				--pointer;
			}
		}
	}

	public ListIterator listIterator() {
		return new ListIteratorImpl();
	}

	private class ListIteratorImpl extends IteratorImpl implements ListIterator {

		@Override
		public boolean hasPrevious() {
			if (getPointer() >= 0) {
				return true;
			}
			return false;
		}

		@Override
		public Object previous() {
			setFlag(false);
			setNextOrPrevious(true);
			setPointer(getPointer() - 1);
			return toArray()[getPointer()+1];
		}

		@Override
		public void set(Object e) {
			if (isFlag()) {
				throw new IllegalStateException();
			}
			if (isNextOrPrevious()) {
				toArray()[getPointer() + 1] = e;
			}
			if (!isNextOrPrevious()) {
				toArray()[getPointer()] = e;
			}
			setFlag(true);
		}

		@Override
		public void remove() {
			if (isFlag()) {
				throw new IllegalStateException();
			}
			super.remove();
		}
	}

}
