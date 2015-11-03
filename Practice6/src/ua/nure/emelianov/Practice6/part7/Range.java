package ua.nure.emelianov.Practice6.part7;

import java.util.Arrays;
import java.util.Iterator;

public class Range implements Iterable<Integer> {

	Range(int start, int end, boolean reverse) {
		setStart(start);
		setEnd(end);
		setReverse(reverse);
		arr = new int[getEnd() - getStart() + 1];
		fillArray();
	}

	private int[] arr;

	public int[] getArr() {
		return Arrays.copyOf(arr, arr.length);
	}

	private boolean reverse;
	private int start;
	private int end;

	public boolean isReverse() {
		return reverse;
	}

	public final void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

	public final int getStart() {
		return start;
	}

	public final void setStart(int start) {
		this.start = start;
	}

	public final int getEnd() {
		return end;
	}

	public final void setEnd(int end) {
		this.end = end;
	}

	public final void fillArray() {
		int local = getStart();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = local;
			local++;
		}
	}

	public void output() {
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

	private class RangeIterator implements Iterator<Integer> {

		private int pointer;

		public int getPointer() {
			return pointer;
		}

		public final void setPointer(int pointer) {
			this.pointer = pointer;
		}

		RangeIterator() {
			if (reverse) {
				setPointer(arr.length);
			} else {
				setPointer(-1);
			}
		}

		@Override
		public boolean hasNext() {
			if (reverse) {
				if (getPointer() - 1 >= 0) {
					return true;
				}
				return false;
			} else {
				if (getPointer() + 1 < arr.length) {
					return true;
				}
			}
			return false;
		}

		@Override
		public Integer next() {
			if (reverse) {
				setPointer(getPointer() - 1);
				return arr[pointer];
			} else {
				setPointer(getPointer() + 1);
				return arr[pointer];
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
