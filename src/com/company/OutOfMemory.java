package com.company;

/*
* @How to set JVM memory
*   $java -Xmx512m OutOfMemory  -- 512M
*   After trying, the default memory size is around 650M
*
*
* */

public class OutOfMemory {
    public static void main(String[] args) {

        Integer[] array = new Integer[1024*1024*650];
        System.out.println("Done");

    }
}





