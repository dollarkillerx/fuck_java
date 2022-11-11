package com.mooc.demo4;

public class SingletonV2 {
    private SingletonV2() {

    }

    private static SingletonV2 instance = null;

    public static SingletonV2 getInstance() {
        if (instance == null) {
            instance = new SingletonV2();
        }

        return instance;
    }
}
