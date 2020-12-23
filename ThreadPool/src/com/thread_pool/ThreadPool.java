package com.thread_pool;

import java.util.ArrayList;

public class ThreadPool {
    ArrayList<Executor> pool;
    final Scheduler scheduler;

    boolean running = true;

    public ThreadPool(int n, int countTasks)
    {
        pool = new ArrayList<>();
        scheduler = new Scheduler(countTasks);
        for(int i = 0; i < n; i++) {
            Executor ex = new Executor(scheduler);
            ex.start();
            pool.add(ex);
        }
    }

    public void complete() {
        running = false;
        for(Executor ex : pool) {
            ex.kill();
        }
        synchronized(scheduler) {
            scheduler.notifyAll();
        }
    }
}

class Scheduler {
    private final ArrayList<Integer> tasks;
    public Scheduler(int n) {
        tasks = new ArrayList<>();
        for( int i = 0; i < n; i++ ) {
            tasks.add(i);
        }
        synchronized(this) {
            this.notifyAll();
        }
    }

    public synchronized Integer getNext() {
        if(tasks.isEmpty()) {
            return null;
        }
        return tasks.remove(0);
    }
}

class Executor extends Thread {
    private boolean running = true;
    private final Scheduler scheduler;

    public Executor(Scheduler s) {
        scheduler = s;
    }

    public void kill() {
        running = false;
    }

    public void run() {
        Integer t = scheduler.getNext();
        while(running || t != null) {
            if(t != null) {
                System.out.println(this.getName() + " is completed Task-" + t);
            }
            else {
                synchronized(scheduler) {
                    try {
                        scheduler.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            t = scheduler.getNext();
        }
    }
}
