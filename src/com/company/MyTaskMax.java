package com.company;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class MyTaskMax implements Callable<Double> {

    private String filePath;
    private double maxValue=0;

    public MyTaskMax(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Double call() throws Exception {
        Scanner s = new Scanner(new File(filePath));
        double temporatyValue=0;
        while (s.hasNext()) {
            temporatyValue=Double.parseDouble(s.next());
        if(temporatyValue>maxValue)
        {
            maxValue=temporatyValue;
        }
        }
        s.close();

        return maxValue;
    }
}
