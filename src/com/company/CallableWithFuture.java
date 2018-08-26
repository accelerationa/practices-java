package com.company;


import java.util.concurrent.*;

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






