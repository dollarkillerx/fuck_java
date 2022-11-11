package com.mooc.demo2;

public class Demo2 {
    public static void main(String[] args) {
        Demo2.test1();
        System.out.println("=========================");
        Demo2.test2();
    }

    private static void test1() {
        Dog dog = new Dog("亚兵", "丫丫", 3,"re",3);
        dog.eat();
        dog.run();
        dog.sleep();
    }

    private static void test2() {
        Dog dog = new Dog("亚兵");
        dog.eat();
        dog.run();
        dog.sleep();
    }
}

