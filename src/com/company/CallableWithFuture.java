package com.company;


import java.util.concurrent.*;

/*
* @Future:
*   Future: Future.get(): blocking wait
*   Future.isDone() is non-blocking, you can check isDone() the use .get() if you want
*   get(long timeout, TimeUnit unit) will only block for a specified time out.
*   By using Future, callable with scheduledExecutorService, you can pass the exception to main and handle from there
*
* @Callable
*   Compared to Runnable, Callable can throw exceptions and return values
*   The returned can be processed using Future
* */

public class CallableWithFuture {
    private static final int TIME = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyCallable myCallable = new MyCallable();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Future<String> future = scheduledExecutorService.schedule(myCallable, TIME, TimeUnit.SECONDS);

        while (true) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                System.out.println("Exception caught");
            }
            future = reInit(scheduledExecutorService, myCallable);
        }
    }

    private static Future reInit(ScheduledExecutorService scheduler, MyCallable myCallable) {
        return scheduler.schedule(myCallable, TIME, TimeUnit.SECONDS);
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call( )  throws Exception {
        System.out.println("In call method");
        throw  new Exception();
    }
}






