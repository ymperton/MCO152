package com.company;

import javax.mail.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

interface IReadMail {
    ArrayList<EmailMessage> readMail(String user, String password);
}

public class ReadMail {
    private IReadMail mail;

    public ReadMail(IReadMail mail) {
        this.mail = mail;
    }

    public ArrayList<EmailMessage> readMail(String user, String password) {
        return mail.readMail(user, password);
    }

}

class GoogleEmailReader implements IReadMail {

    @Override
    public ArrayList<EmailMessage> readMail(String user, String password) {
        String host = "pop.gmail.com";

        ArrayList<EmailMessage> emailList = new ArrayList<>();

        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");
            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();

            String subject, from, msg;
            Date dateReceived;

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];

                subject = message.getSubject();
                from = message.getFrom()[0].toString();
                msg = message.getContent().toString();
                dateReceived = message.getReceivedDate();

                emailList.add(new EmailMessage(subject, from, user, msg, dateReceived));
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailList;
    }
}