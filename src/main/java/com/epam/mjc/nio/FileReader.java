package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileReader {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        Path wiki_path = Paths.get("src/main/resources/Profile.txt");
        fileReader.getDataFromFile(wiki_path.toFile());
    }

    public Profile getDataFromFile(File file) {
        Charset charset = Charset.forName("ISO-8859-1");
        String[] cleanedData = new String[4];
        try {
            List<String> lines = Files.readAllLines(file.toPath(), charset);
            System.out.println(lines);
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
