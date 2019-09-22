package xyz.lannt.truyencvcollector.application.service;

import static xyz.lannt.truyencvcollector.utilities.NumberUtility.toInt;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.truyencvcollector.application.client.gmail.GmailClient;
import xyz.lannt.truyencvcollector.application.client.truyencv.TruyenCvClient;
import xyz.lannt.truyencvcollector.application.properties.TruyenCvProperty;

@Service
public class CollectionService {

    @Autowired
    private TruyenCvProperty truyenCvProperty;
    @Autowired
    private TempFileService tempFileService;
    @Autowired
    private GmailClient gmailClient;

    public void get(String name, String from, String to) {
        int fromIndex = toInt(from);
        int toIndex = toInt(to);
        TruyenCvClient client = new TruyenCvClient(truyenCvProperty);

        File tmpFile = tempFileService.create(name + "_" + from + (toIndex == 0 ? "" : "-" + to));
        if (toIndex == 0) {
            tempFileService.write(tmpFile, client.request(name, from));
        } else {
            getMultiple(client, name, fromIndex, toIndex, tmpFile);
        }

        gmailClient.sendEmail(tmpFile.getPath());
    }

    private String getMultiple(TruyenCvClient client, String name, int from, int to, File tmp) {
        String filePath = "";
        for (int chapter = from; chapter <= to; chapter++) {
            StringBuffer sb = new StringBuffer();
            sb.append(client.request(name, Integer.toString(chapter)));
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());

            tempFileService.write(tmp, sb.toString());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return filePath;
    }
}
