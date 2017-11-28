package com.company;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.*;


interface IMailer {
    void sendMail(String to, String subject, String message);
}


class MailTask extends TimerTask {
    private IMailer mail;
    private String to, subject, message;

    public MailTask(IMailer mail) {
        this.mail = mail;
    }

    public void writeMail(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public void run() {
        mail.sendMail(to, subject, message);
    }
}

class GoogleEmail implements IMailer {

    @Override
    public void sendMail(String to, String subject, String message) {
        final String username = "shtarkknight@gmail.com";
        final String password = "Bera64chos@a";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("shtarkknight@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(message);

            Transport.send(msg);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

class ClockMailer {
    IClock clock;
    MailTask mailTask;

    public ClockMailer(IClock clock, IMailer mail) {
        this.clock = clock;
        mailTask = new MailTask(mail);
    }

    private LocalDateTime getNow() {
        return clock.now();
    }

    public void writeMail(String to, String subject, String message) {
        mailTask.writeMail("ymperton@gmail.com", "Hello", "Hello World!");
    }

    public void scheduleTask(Calendar dateTime) {

        Timer timer = new Timer();

        // Schedule to run at midnight
        timer.schedule(mailTask,
                dateTime.getTime());
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        final String TIME_SERVER = "time-a-g.nist.gov";
        NTPUDPClient timeClient = new NTPUDPClient();
        InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);

        long computerTimeWhenSentSignal = System.currentTimeMillis();
        TimeInfo timeInfo = timeClient.getTime(inetAddress);
        long computerTimeWhenGotSignalBack = System.currentTimeMillis();

        int timeLag = (int) (computerTimeWhenGotSignalBack - computerTimeWhenSentSignal) / 2;
        long currentTimeInMilliseconds = timeInfo.getReturnTime();
        Date dateTime = new Date(currentTimeInMilliseconds);
        System.out.println(dateTime);
        currentTimeInMilliseconds += timeLag;
        dateTime = new Date(currentTimeInMilliseconds);
        System.out.println(dateTime);
        System.out.println("milliseconds: " + currentTimeInMilliseconds + " lag: " + timeLag);

    }
}
