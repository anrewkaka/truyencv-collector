package xyz.lannt.truyencvcollector.application.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "kindle.sender")
public class KindleSenderProperty {

    private String username;
    private String password;
}
