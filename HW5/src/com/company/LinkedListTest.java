package com.company;

//Edon Freiner & Yisroel Perton

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

class LinkedListTest {
    List list;

    @BeforeEach
    public void create() {
        list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
    }

    //boolean add
    @Test
    public void TestAddsElementAndReturnsTrue() {
        boolean bln = list.add("d");
        assertTrue(bln);
    }

    //add
    @Test
    public void TestAddElementAndItIsThere() {
        assertThat(list.contains("a"), is(true));
    }

    //add at Index
    @Test
    public void TestAddElementToIndexAndItIsThere() {
        list.add(1, "d");
        assertThat(list.indexOf("d"), is(equalTo(1)));
    }

    //addAll
    @Test
    public void TestAddMultipleElementAndItIsThere() {
        List<String> list2 = new LinkedList<>();
        list2.add("d");
        list2.add("e");

        list.addAll(list2);

        assertThat(list.contains("d"), is(true));
        assertThat(list.contains("e"), is(true));
    }

    //addAll at Index
    @Test
    public void TestAddMultipleElementAtIndexAndItIsThere() {
        List<String> list2 = new LinkedList<>();
        list2.add("d");
        list2.add("e");

        list.addAll(1, list2);

        assertThat(list.indexOf("d"), is(1));
        assertThat(list.indexOf("e"), is(2));
    }

    //clear
    @Test
    public void TestClear() {
        list.clear();

        assertThat(list.size(), is(equalTo(0)));
    }

    //contains
    @Test
    public void TestListContainsElementReturnTrue() {
        assertThat(list.contains("a"), is(true));
    }

    @Test
    public void TestListDoesNotContainsElementReturnFalse() {
        assertThat(list.contains("d"), is(false));
    }

    @Test
    public void TestListContainsNullReturnTrue() {
        list.add(null);
        assertThat(list.contains("a"), is(true));
    }

    //contains all
    @Test
    public void TestListContainsMultipleConsecutiveElementsAndReturnsTrue() {
        List<String> list2 = new LinkedList<>();
        list2.add("a");
        list2.add("b");

        assertThat(list.containsAll(list2), is(true));
    }

    @Test
    public void TestListContainsMultipleNonConsecutiveElementsAndReturnsTrue() {
        List<String> list2 = new LinkedList<>();
        list2.add("a");
        list2.add("c");

        assertThat(list.containsAll(list2), is(true));
    }

    @Test
    public void TestListDoesNotContainsMultipleElementsAndReturnsFalse() {
        List<String> list2 = new LinkedList<>();
        list2.add("a");
        list2.add("d");

        assertThat(list.containsAll(list2), is(false));
    }

    //equals
    @Test
    public void TestEqualsOfSameListShouldReturnFalse() {
        List<String> list2 = new LinkedList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");

        assertThat(list.equals(list2), is(true));
    }

    @Test
    public void TestEqualsOfDifferentListShouldReturnFalse() {
        List<String> list2 = new LinkedList<>();
        list2.add("a");
        list2.add("b");
        list2.add("d");

        assertThat(list.equals(list2), is(false));
    }

    @Test
    public void TestGetOfIndexInListShouldReturnItem() {
        assertThat(list.get(1), is(equalTo("b")));
    }

    @Test
    public void TestGetOfIndexNotInListShouldReturnIndexOutOfBoundsError() {
        try {
            list.get(10);
            fail();
        } catch(IndexOutOfBoundsException e) {

        }

    }

    //Index Of
    @Test
    public void TestIndexOf() {
        int index = list.indexOf("b");
        assertThat(index, is(equalTo(1)));
    }

    @Test
    public void indexOfFirstReturnsZero() {
        int index = list.indexOf("a");
        assertThat(index, is(equalTo(0)));
    }

    @Test
    public void indexOfLastIsNotOutOfBounds() {
        int index = list.indexOf("c");
        assertThat(index, is(equalTo(2)));
    }

    //isEmpty
    @Test
    public void TestIsEmptyWhenListIsEmptyShouldReturnTrue() {
        list.clear();
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void TestIsEmptyIfFullShouldReturnFalse() {
        assertThat(list.isEmpty(), is(false));
    }

    //last IndexOf
    @Test
    public void TestLastIndexOf() {
        list.add("a");
        assertThat(list.lastIndexOf("a"), is(equalTo(3)));
    }

    @Test
    public void TestLastIndexOfWhereItemIsNotInListShouldReturnNegativeOne() {
        assertThat(list.lastIndexOf("d"), is(equalTo(-1)));
    }

    @Test
    public void TestRemoveObjectShouldNotBeThere() {
        list.remove("a");
        assertThat(list.lastIndexOf("a"), is(equalTo(-1)));
    }

    @Test
    public void TesetRemoveElementAtIndex() {
        list.remove(0);
        assertThat(list.lastIndexOf("a"), is(equalTo(-1)));
    }

    @Test
    public void TestSetWithinArray() {
        list.set(0, "d");
        assertThat(list.contains("d"), is(true));
        assertThat(list.indexOf("d"), is(equalTo(0)));
    }

    @Test
    public void TestSetOfIndexNotInListShouldReturnIndexOutOfBoundsError() {
        try {
            list.set(12, "d");
            fail();
        } catch (IndexOutOfBoundsException e) {

        }
    }

    //size
    @Test
    public void TestSize() {
        assertThat(list.size(), is(equalTo(3)));
    }

    @Test
    public void subList() {
        List subList = list.subList(1, 3);
        assertThat(subList.contains("b"), is(true));
        assertThat(subList.contains("c"), is(true));
        assertThat(subList.size(), is(equalTo(2)));
    }

    @Test
    public void TestToArray() {
        Object[] array = list.toArray();

        for (int i = 0; i < list.size(); i++) {
            assertThat(array[i], is(equalTo(list.get(i))));
        }
    }

    @Test
    public void TestToArrayTypeT() {
        List<String> newList = new ArrayList<>();
        newList.add("a");
        newList.add("b");
        newList.add("c");

        String[] array = newList.toArray(new String[newList.size()]);


        for (int i = 0; i < list.size(); i++) {
            assertThat(array[i], is(equalTo(list.get(i))));
        }
    }

    @Test
    public void TestRemoveAll() {

        Collection<String> collection = new ArrayList<>();
        collection.add("b");
        collection.add("c");

        list.removeAll(collection);

        assertThat(list.contains("a"), equalTo(true));
        assertThat(list.contains("b"), not(equalTo(true)));
        assertThat(list.contains("c"), not(equalTo(true)));
    }


    @Test
    public void TestRetainAll() {

        Collection<String> collection = new ArrayList<>();
        collection.add("b");
        collection.add("c");

        list.retainAll(collection);

        assertThat(list.contains("a"), not(equalTo(true)));
        assertThat(list.contains("b"), equalTo(true));
        assertThat(list.contains("c"), equalTo(true));

    }

    @Test
    public void TestIterator() {
        assertTrue(list.iterator() instanceof Iterator);
    }

    @Test
    public void TestListIterator() {
        assertTrue(list.listIterator() instanceof ListIterator);
    }

    @Test
    public void TestListIteratorIndex() {
        assertTrue(list.listIterator(1).next().toString() == "b");

    }


}