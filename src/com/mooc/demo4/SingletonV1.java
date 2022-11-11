package com.mooc.demo4;

public class SingletonV1 {
    // 1. 定义私有构造
    private SingletonV1() {

    }

    // 2. 私有静态实例
    private static SingletonV1 instance = new SingletonV1();

    // get
    public static SingletonV1 getInstance() {
        return instance;
    }

}
