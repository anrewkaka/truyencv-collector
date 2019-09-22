package xyz.lannt.truyencvcollector.application.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class TempFileService {

    public String saveTmpFile(String name, String content, String timestamp) {
        try {
            // create a temp file
            File temp = File.createTempFile(name + "_" + timestamp, ".txt");

            // write it
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));
            bw.write(content);
            bw.close();

            return temp.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
