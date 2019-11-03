package xyz.lannt.truyencvcollector.application.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

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
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(temp, true), StandardCharsets.UTF_8));
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
