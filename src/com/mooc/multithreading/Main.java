package com.mooc.multithreading;

public class Main {
    public static void main(String[] args) {
        ThreadAb threadAb = new ThreadAb();
        threadAb.start();

        for (;;) {
            try {
                Thread.sleep(1000);
                System.out.println("ppppppppppppppppp");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


class ThreadAb extends Thread{

    @Override
    public synchronized void start() { // 启动线程
        System.out.println("Start");
        super.start();
    }

    @Override
    public void run() { // 新线程中执行的方法
        System.out.println("Run......");
        super.run();
        for (;;) {
            try {
                Thread.sleep(1000);
                System.out.println("Run...........");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}