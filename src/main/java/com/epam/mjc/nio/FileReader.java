package com.epam.mjc.nio;

import java.io.*;

public class FileReader {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        fileReader.getDataFromFile(file);
    }

    public Profile getDataFromFile(File file) throws IOException {
        String[] cleanedData = new String[4];
        java.io.FileReader fileReader = new java.io.FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String n;
        int i = 0;
        while ((n = bufferedReader.readLine()) != null){
            String data = n.split(":")[1].strip();
            cleanedData[i] = data;
            i++;
        }
        fileReader.close();

        return new Profile(
                cleanedData[0],
                Integer.valueOf(cleanedData[1]),
                cleanedData[2],
                Long.parseLong(cleanedData[3])
        );
    }
}
