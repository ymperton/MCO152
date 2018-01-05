package com.company;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        String[] credentials = getCredentials();
        String receivingUsername = credentials[0];
        String receivingPassword = credentials[1];
        ReadMail rm = new ReadMail(new GoogleEmailReader());
        ArrayList<EmailMessage> listOfEmailsFromReceivalEmail = new ArrayList<>();

        //read all emails from first email.
        log.trace("Attempting to retrieve emails from email: " + receivingUsername);
        try {
            listOfEmailsFromReceivalEmail = rm.readMail(receivingUsername, receivingPassword);
            log.info("There are " + listOfEmailsFromReceivalEmail.size() + " emails.");
        } catch (Exception e) {
            String output = "";
            for (StackTraceElement s : e.getStackTrace()) {
                output += s + "\n";
            }
            log.error(output);
        }

        //send out all messages to second email.
        log.trace("Now sending out all emails");
        sendOutEmails(listOfEmailsFromReceivalEmail);


    }

    private static void sendOutEmails(ArrayList<EmailMessage> listOfEmailsFromReceivalEmail) {
        SendMail mt = new SendMail(new GoogleEmailSender());
        String emailToSendTo = "";
        String defaultSubjectMessage = "Automatic Forward";

        for (EmailMessage email : listOfEmailsFromReceivalEmail) {
            log.trace("Attempting to send email:\n" + email.toString());
            emailToSendTo = email.getSubject();
            log.info("Sending it to " + emailToSendTo);
            if (isEmail(emailToSendTo)) {
                mt.sendMail(emailToSendTo, defaultSubjectMessage, email.getMessage());
            }
            log.trace(email);
        }
    }

    private static boolean isEmail(String emailToSendTo) {
        return (emailToSendTo.contains("@"));
    }

    public static String[] getCredentials() {
        String[] credentials = new String[2];
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("credentials.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        credentials[0] = fileScanner.nextLine();
        credentials[1] = fileScanner.nextLine();

        return credentials;
    }
}
