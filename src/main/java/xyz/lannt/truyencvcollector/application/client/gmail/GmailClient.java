package xyz.lannt.truyencvcollector.application.client.gmail;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class GmailClient {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${kindle.email}")
    private String targetMail;

    public void sendEmail(String filePath) {

        try {
            MimeMessage msg = javaMailSender.createMimeMessage();

            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(targetMail);

            File attaching = new File(filePath);
            helper.setSubject(attaching.getName());
            helper.setText("Hello");

            String attachmentName = attaching.getName();
            Pattern p = Pattern.compile("_[\\d]*.txt");
            Matcher m = p.matcher(attachmentName);

            helper.addAttachment(m.replaceAll(".txt"), attaching);

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
