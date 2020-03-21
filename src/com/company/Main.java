package com.company;

import javafx.concurrent.Task;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void generateFiles(List<String> files) {
        for (String s : files) {
            try (PrintStream ps = new PrintStream(s);) {
                Random r = new Random();
                for (int i = 0; i < 1_000_000; i++) {
                    double val = r.nextDouble() * 100;
                    ps.println(val);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<String> srcFiles = List.of("plikA.txt", "plikB.txt", "plikC.txt", "plikD.txt", "plikE.txt", "plikF.txt", "plikG.txt");
        List<String> dstFiles = List.of("plikADst.txt", "plikBDst.txt", "plikCDst.txt", "plikDDst.txt", "plikEDst.txt", "plikFDst.txt", "plikGDst.txt");
        final int NUMBER_THREADS = 5;
        long timeStart;
        long timeStop;

        //generateFiles
         generateFiles(srcFiles);

        ExecutorService pool = Executors.newFixedThreadPool(NUMBER_THREADS);
        Iterator<String> srcFilesIterator = srcFiles.iterator();
        Iterator<String> dstFilesIterator = dstFiles.iterator();

        //time start
        timeStart = System.currentTimeMillis();

        while (srcFilesIterator.hasNext() && dstFilesIterator.hasNext()) {

            MyTaskCopy myTaskCopy = new MyTaskCopy(srcFilesIterator.next(), dstFilesIterator.next(), pool);
            Future<?> result = pool.submit(myTaskCopy);
        }
        pool.shutdown();
        //time stop
        timeStop = System.currentTimeMillis();


        System.out.println("Time: "+(timeStop - timeStart));
    }
}
