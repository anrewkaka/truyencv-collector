package xyz.lannt.truyencvcollector.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "truyencv")
public class TruyenCvProperty {

    private String baseUri;
    private String titlePath;
    private String contentPath;
    private String chapterPrefix;
}
