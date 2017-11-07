package com.company;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

class LinkedListTest {
    List list;

    @Before
    public void create() {
        list = new LinkedList<>();
    }

    //boolean add
    @Test
    public void TestAddsElementAndReturnsTrue() {
        List list = new LinkedList<>();
        boolean bln = list.add("A");
        assertTrue(bln);
    }

    @Test
    public void TestAddsNullElementReturnsFalse() {
        List<Integer> list = new LinkedList<>();
        boolean bln = list.add(null);
        assertTrue(bln);
    }

    @Test
    public void TestAddElementToListAndItIsThere() {
        List<String> list = new LinkedList<>();
        list.add("A");
        assertTrue(list.contains("A"));
        assertThat(list.size(), is(equalTo(1)));
        assertThat(list.contains("A"), is(true));
    }

    @Test
    public void TestAddMultipleElementToListAndItIsThere() {
        List<String> list = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        list2.add("A");
        list2.add("B");

        list.addAll(list2);

        assertThat(list.contains("A"), is(true));
        assertThat(list.contains("B"), is(true));
    }

    //clear
    @Test
    public void TestClear() {
        List<String> list = new LinkedList<>();
        list.add("A");
        list.clear();

        assertThat(list.size(), is(equalTo(0)));
    }

    @Test
    public void TestListWithElementContainsReturnTrue() {
        List<String> list = new LinkedList<>();
        list.add("A");

        assertThat(list.contains("A"), is(true));
    }




}