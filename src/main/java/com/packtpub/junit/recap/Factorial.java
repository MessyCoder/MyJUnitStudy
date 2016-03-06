package com.packtpub.junit.recap;

/**
 * Created by pan on 2016/3/5.
 */
public class Factorial {


    public long factorial(long number) {
        if(number == 0) {
            return 1;
        }
        return number*factorial(number-1);
    }
}
