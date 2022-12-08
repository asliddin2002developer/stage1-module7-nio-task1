package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;


public class FileReader {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        fileReader.getDataFromFile(file);
    }

    public Profile getDataFromFile(File file) {
        String[] cleanedData = new String[4];
        try {
            Charset charset = Charset.forName("ISO-8859-1");
            List<String> lines = Files.readAllLines(file.toPath(), charset);
            int i = 0;
            for (String line : lines) {
                String[] currentData = line.split(":");
                cleanedData[i] = currentData[1].strip();
                i++;
            }
        }
        catch (IOException e) {
            e.toString();
        }
        return new Profile(
                cleanedData[0],
                Integer.valueOf(cleanedData[1]),
                cleanedData[2],
                Long.parseLong(cleanedData[3])
        );
    }
}
