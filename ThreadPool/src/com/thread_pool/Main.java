package com.thread_pool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadPool pools = new ThreadPool( 4, 50);
        pools.complete();
    }
}
