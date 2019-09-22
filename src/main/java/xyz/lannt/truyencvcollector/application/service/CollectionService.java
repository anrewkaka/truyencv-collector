package xyz.lannt.truyencvcollector.application.service;

import static xyz.lannt.truyencvcollector.utilities.NumberUtility.toInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.lannt.truyencvcollector.application.client.truyencv.TruyenCvClient;
import xyz.lannt.truyencvcollector.application.properties.TruyenCvProperty;

@Service
public class CollectionService {

    @Autowired
    private TruyenCvProperty truyenCvProperty;

    public String get(String name, String from, String to) {
        int fromIndex = toInt(from);
        int toIndex = toInt(to);
        TruyenCvClient client = new TruyenCvClient(truyenCvProperty);

        if (toIndex == 0) {
            return client.request(name, from);
        }

        return getMultiple(client, name, fromIndex, toIndex);
    }

    private String getMultiple(TruyenCvClient client, String name, int from, int to) {
        StringBuffer sb = new StringBuffer("");
        for (int chapter = from; chapter <= to; chapter++) {
            sb.append(client.request(name, Integer.toString(chapter)));
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
