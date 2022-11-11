package com.mooc.demo2;

public class Dog {
    String breed; // 品种
    String nickname;
    int size;
    String colour;
    int age;

    // 构造方法
    public Dog(String breed,String nickname, int size, String colour, int age) {
        this.breed = breed;
        this.nickname = nickname;
        this.size = size;
        this.colour = colour;
        this.age = age;
    }

    public Dog(String nickname) {
        this.nickname = nickname;
    }

    void eat() {
        System.out.printf("%s eat\n",this.nickname);
    }

    void run() {
        System.out.printf("%s run\n",this.nickname);

    }

    void sleep() {
        System.out.printf("%s sleep\n",this.nickname);
    }

    void name() {
        System.out.printf("%s\n",this.nickname);
    }
}
