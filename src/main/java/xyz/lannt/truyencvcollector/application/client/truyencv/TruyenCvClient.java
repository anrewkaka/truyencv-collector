package xyz.lannt.truyencvcollector.application.client.truyencv;

import java.io.IOException;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
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
            doc = Jsoup.connect(url).timeout(60000).userAgent(
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
            String tmpContent = doc.select(property.getContentPath()).toString();
            OutputSettings settings = new OutputSettings();
            settings.prettyPrint(false);
            String content = Jsoup.clean(tmpContent, "", Whitelist.none(), settings);

            result.append(title.orElse(""));
            result.append(System.lineSeparator());
            result.append(content);
        } catch (SelectorParseException ex) {
            throw new TruyenCvClientException(ex);
        }

        return result.toString();
    }
}
