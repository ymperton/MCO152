package com.company;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void TestAddsElementAndReturnsBoolean() {
        List list = new LinkedList<>();
        boolean bln = list.add("A");
//        (bln);
    }
}