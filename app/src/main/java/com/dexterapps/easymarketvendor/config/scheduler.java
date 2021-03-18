package com.dexterapps.easymarketvendor.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class scheduler {


    public static void schedule(Loader loader) {

        ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                loader.cancel();

            }
        };

        worker.schedule(runnable, 1000, TimeUnit.MILLISECONDS);
    }

}
