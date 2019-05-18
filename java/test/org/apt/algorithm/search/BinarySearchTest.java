package org.apt.algorithm.search;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BinarySearchTest {

    @Test
    public void testCopy(){
        int[] source = {1,2,3};
        int[] copy = Arrays.copyOf(source, source.length);
        assertArrayEquals(source,copy);
    }

    @Test
    public void testSearch() {
        Integer[] source = {0,1,2,3,4,5,6,7,8,9,10};
        BinarySearch search = new BinarySearch(source);
        assertEquals(-1, search.search(-11));
        assertEquals(1, search.search(1));
        assertEquals(3, search.search(3));
        assertEquals(5, search.search(5));
        assertEquals(6, search.search(6));
        assertEquals(8, search.search(8));
        assertEquals(10, search.search(10));
        assertEquals(-1, search.search(11));

    }

    @Test
    public void testUnmodify() {
        Integer[] source = {1,2,3,4,5};
        BinarySearch search = new BinarySearch(source);
        source[1] = 1;
        assertEquals(1, search.search(2));
    }

}
