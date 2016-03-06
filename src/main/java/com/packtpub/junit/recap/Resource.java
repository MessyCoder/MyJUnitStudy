package com.packtpub.junit.recap;

/**
 * Created by pan on 2016/3/6.
 */
public class Resource {
    public void open() {
        System.out.println("Opened");
    }
    public void close() {
        System.out.println("Closed");
    }
    public double get() {
        return Math.random();
    }
}
