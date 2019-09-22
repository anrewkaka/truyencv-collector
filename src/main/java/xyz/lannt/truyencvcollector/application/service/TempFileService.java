package xyz.lannt.truyencvcollector.application.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class TempFileService {

    public File create(String name) {
        // create a temp file
        try {
            return File.createTempFile(name, ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(File temp, String content) {
        try {
            // write it
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
