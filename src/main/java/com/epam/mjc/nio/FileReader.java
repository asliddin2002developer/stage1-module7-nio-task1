package com.epam.mjc.nio;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileReader {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        File file = new File("src/main/resources/Profile.txt");
        fileReader.getDataFromFile(file);
    }

    public Profile getDataFromFile(File file) {
        StringBuilder bld = new StringBuilder();

        try (RandomAccessFile aFile = new RandomAccessFile(file.getPath(), "r");
             FileChannel inChannel = aFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (inChannel.read(buffer) > 0) {
                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++) {
                    bld.append((char) buffer.get());
                }
                buffer.clear(); // do something with the data and clear/compact it.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String stringContentOfFile = bld.toString();

        String[] parsedArg = new String[4];
        String[] keyVals = stringContentOfFile.split("\n");
        for (int i = 0; i < keyVals.length; i++) {
            String[] parts = keyVals[i].split(":",2);
            parsedArg[i] = parts[1].trim();
        }

        return new Profile(
                parsedArg[0],
                Integer.valueOf(parsedArg[1]),
                parsedArg[2],
                Long.parseLong(parsedArg[3]));
    }
}
