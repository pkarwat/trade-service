package com.example.tradeservice.shared;

import java.io.FileInputStream;
import java.io.IOException;

public class MyFileUtils {

    public static byte[] readCsvFile(String filePath) {
        try(FileInputStream fileIn = new FileInputStream(filePath)) {
            return fileIn.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
