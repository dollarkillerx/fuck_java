package com.mooc.multithreading;

import java.util.concurrent.ConcurrentHashMap;

public class Main2 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
        Tx tx = new Tx(userMap);
        Thread t1 = new Thread(tx);
        t1.start();

        for (;;) {
            try {
                Thread.sleep(998);

                User dollarkiller = userMap.get("dollarkiller");
                if (dollarkiller == null) {
                    userMap.put("dollarkiller", new User());
                    continue;
                }
                System.out.printf("Main Thread: %s \n", dollarkiller.name);

                dollarkiller.name = "dok tx 2";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

class User {
    String name;
}

class Tx implements Runnable {

    ConcurrentHashMap<String, User> userMap;

    public Tx(ConcurrentHashMap<String,User> userMap) {
        this.userMap = userMap;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                Thread.sleep(1000);

                User dollarkiller = userMap.get("dollarkiller");
                if (dollarkiller == null) {
                    userMap.put("dollarkiller", new User());
                    continue;
                }
                System.out.printf("New Thread: %s \n", dollarkiller.name);
                dollarkiller.name = "dok tx";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}