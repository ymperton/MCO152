package com.company;

public class Main {

    public static void main(String[] args) {
        fizzbuzz();
    }

    private static void fizzbuzz() {
        boolean flag;
        String output = "";
        for (int i = 0; i < 100; i++) {
            flag = false;
            if (i % 3 == 0) {
                output = "Fizz";
                flag = true;
            }
            if (i % 5 == 0) {
                output = "Buzz";
                flag = true;
            }
            if (!flag) {
                output = String.valueOf(i);
            }
            System.out.println(output);
        }
    }
}
