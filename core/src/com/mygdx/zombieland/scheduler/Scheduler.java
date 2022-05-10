package com.mygdx.zombieland.scheduler;

public class Scheduler {

    public void runTaskAfter(final Runnable runnable, final long afterMillis) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long last = System.currentTimeMillis();
                while (System.currentTimeMillis() < last + afterMillis) {}
                runnable.run();
            }
        }).start();
    }

}
