package com.company;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;

public class MyTaskCopy implements Runnable {

    private String sourceFile;
    private String destiantionFile;
   private ExecutorService executorService;

    public MyTaskCopy(String sourceFile, String destiantionFile, ExecutorService executorService) {
        this.sourceFile = sourceFile;
        this.destiantionFile = destiantionFile;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(sourceFile);
            os = new FileOutputStream(destiantionFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
