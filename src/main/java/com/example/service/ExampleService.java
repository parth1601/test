package com.example.service;

import com.example.config.Config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

@ApplicationScoped
public class ExampleService {

    @Inject
    Config config;

    public void readMails() {

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", "imap.gmail.com");


        try {
            Session session = Session.getDefaultInstance(properties, null);
            Store store = session.getStore("imaps");
            store.connect(config.credentials().host(), config.credentials().username(), config.credentials().password());

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                Object content = message.getContent();
                if (content instanceof String) {
                    System.out.println("Content: " + content);
                }

                System.out.println("------------------------");
                break;
            }

            // Close the folder and store
            inbox.close(false);
            store.close();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}