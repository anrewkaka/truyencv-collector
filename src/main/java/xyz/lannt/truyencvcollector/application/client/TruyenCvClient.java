package xyz.lannt.truyencvcollector.application.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector.SelectorParseException;

import lombok.AllArgsConstructor;
import xyz.lannt.truyencvcollector.application.properties.TruyenCvProperty;

@AllArgsConstructor
public class TruyenCvClient {

    private TruyenCvProperty property;

    public String request(String name, String chapter) {
        String url = property.getBaseUri() + "/" + name + "/" + property.getChapterPrefix() + chapter;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                    .get();
        } catch (IOException e) {
            throw new TruyenCvClientException(e);
        }
        StringBuffer result = new StringBuffer("");

        try {
            // get chapter title
            Optional<String> title = doc.select(property.getTitlePath()).stream().map(Element::text)
                    .reduce((e1, e2) -> e1.concat(e2));
            Optional<String> content = doc.select(property.getContentPath()).stream().map(Element::text)
                    .reduce((e1, e2) -> e1.concat(e2));

            result.append(title.orElse(""));
            result.append(System.lineSeparator());
            result.append(content.orElse(""));
        } catch (SelectorParseException ex) {
            throw new TruyenCvClientException(ex);
        }

        return result.toString();
    }

    private StringBuffer request(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        // external search condition
        // + "&hq=" + this.encodeUrl(and)
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-Type", "application/json");
        HttpResponse response;
        try {
            response = client.execute(get);
        } catch (IOException e) {
            throw new TruyenCvClientException(e);
        }
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new TruyenCvClientException("Request error: " + response.getStatusLine().getStatusCode());
        }

        StringBuffer result = new StringBuffer();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (UnsupportedOperationException | IOException e) {
            throw new TruyenCvClientException(e);
        }

        return result;
    }
}
