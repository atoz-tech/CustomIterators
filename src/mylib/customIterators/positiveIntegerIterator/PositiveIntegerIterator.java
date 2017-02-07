package mylib.customIterators.positiveIntegerIterator;

import java.util.Arrays;
import java.util.Iterator;

/*
 * Goal: Given an Iterator of type Integer, provide an Iterator<Integer> implementation
 *       that only provides non-negative values. Only implement next() and hasNext()
 *
 * Input: Iterator of type Integer
 * Output: next() should only ever return positive integers, hasNext() should only return
 *         true if there is a positive integer ahead in the underlying Iterator
 *
 * Notes:
 *      remove() does not need to be implemented
 *      testIter() is a test case that should pass
 */
public class PositiveIntegerIterator implements Iterator<Integer> {

    Iterator<Integer> intItr;

    Integer next = 0; // this holds the next positive integer if there is one; '0' otherwise

    public PositiveIntegerIterator(Iterator<Integer> i) {
        this.intItr = i;
        // moving the cursor to the first positive integer
        iterateUntilPositiveInt();
    }

    @Override
    public boolean hasNext() {
        return next > 0;
    }

    @Override
    public Integer next() { // throws NoSuchElementException
        Integer tmp = next; // value to be returned
        next = 0; // initializing to zero assuming there might not be next positive integer
        // move the cursor to the next positive integer before returning
        iterateUntilPositiveInt();
        return tmp;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void iterateUntilPositiveInt() {
        // this moves the cursor to the next positive integer while handling the null values
        while (intItr.hasNext() && ((next = intItr.next()) == null || next < 1));
    }

    public static void main(String[] args) {
        Iterator<Integer> intIter = Arrays.asList(new Integer[] {null, -1, 2, null, 15, -4, 50, null, 0}).iterator();
        Iterator<Integer> iter = new PositiveIntegerIterator(intIter);
        // multiple hasNext() calls succeed
        for (int i = 0; i < 100; i++) {
            assert iter.hasNext();
        }
        // values are correct
        assert iter.hasNext();
        assert iter.next() == 2;
        // no hasNext() call...
        assert iter.next() == 15;
        assert iter.hasNext();
        assert iter.next() == 50;
        assert iter.hasNext() == false;
    }
}
