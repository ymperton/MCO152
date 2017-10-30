package com.company;

public class Main {

    public static void main(String[] args) {
        fizzbuzz();
    }

    private static void fizzbuzz() {
        boolean flag;
        for (int i = 0; i < 100; i++) {
            flag = false;
            if (i % 3 == 0) {
                System.out.print("Fizz");
                flag = true;
            }
            if (i % 5 == 0) {
                System.out.print("Buzz");
                flag = true;
            }
            if (!flag) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
