package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class MainThree {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<String> srcFiles = List.of("plikA.txt", "plikB.txt", "plikC.txt", "plikD.txt", "plikE.txt", "plikF.txt", "plikG.txt");
        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Callable<Double>> taskList = new ArrayList<>();

        srcFiles.forEach(k -> taskList.add(new MyTaskMax(k)));

        List<Future<Double>> futures = pool.invokeAll(taskList);

        pool.shutdown();
        futures.forEach((k) -> {
            try {
                System.out.println(k.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}
