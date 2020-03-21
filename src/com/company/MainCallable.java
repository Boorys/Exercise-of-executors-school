package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> srcFiles = List.of("plikA.txt", "plikB.txt", "plikC.txt", "plikD.txt", "plikE.txt", "plikF.txt", "plikG.txt");
        final int NUMBER_THREADS = 5;
        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_THREADS);

        Iterator<String> srcFilesIterator = srcFiles.iterator();
        List<Future<Double>> futureList = new ArrayList<>();

        while (srcFilesIterator.hasNext()) {
            MyTaskMax myTaskMax = new MyTaskMax(srcFilesIterator.next());
            futureList.add(pool.submit(myTaskMax));
        }


        pool.shutdown();

        futureList.forEach(k-> {
            try {
                System.out.println(k.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });



    }


}
