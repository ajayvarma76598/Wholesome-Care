package com.stackroute.wellnessservice.service;


import com.stackroute.wellnessservice.Repository.HelplineRepository;
import com.stackroute.wellnessservice.model.EmailRequest;
import com.stackroute.wellnessservice.model.GuestUser;
import com.stackroute.wellnessservice.model.Helpline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

@Service
public class MailServiceImpl implements MailService{
    GuestUser guestUser;
    HelplineRepository helplineRepository;
    Helpline helpline;
    EmailRequest emailRequest;
//    @Value("${helpline.csv.filepath}")
//    private String csvFilePath;

    @Override
    public boolean sendEmail(String to, String subject, String message,String cityName) throws Exception {
        Set<Helpline> helplineDetails = new HashSet<>();
        boolean foo=false;
        String senderEmail="wholesomecare2021@gmail.com";
        String senderPassword="wholesome.c@re";

        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getDefaultInstance(properties, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication(){

                return new PasswordAuthentication(senderEmail,senderPassword);
            }
        });

        String Number = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("./assets/helplineNumbers.csv"))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if(data[0].equals(cityName)) {
                    Number=data[1];
                    helplineDetails.add(new Helpline(data[0], data[1]));
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            MimeMessage msg=new MimeMessage(session);
            MimeMessageHelper helper=new MimeMessageHelper(msg,true);
            helper.setFrom(new InternetAddress(senderEmail));
            msg.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(to)));
            helper.setSubject("wholesomecare Helpline Details");
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart textMime = new MimeBodyPart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart messageBodyPart1=new MimeBodyPart();
            MimeBodyPart messageBodyPart2=new MimeBodyPart();
            String content = "Hi Welcome to Wholesome care,<br><i><b>Sometimes life will Kick you around but sooner or later you realize you're not just a survivor you're a warrior and you're stronger than anything life throws your way.</b></i>" + "<br><img src='cid:image' width=600 height=350 />";
            messageBodyPart.setContent(content,"text/html; charset=utf-8");
            DataSource fds = new FileDataSource(
                    "./assets/image.png");
            messageBodyPart1.setDataHandler(new DataHandler(fds));
            messageBodyPart1.setHeader("Content-ID", "<image>");
            textMime.setText("\nTo get Depression Free call to the helpline number below\n");
            String content1="<b>Helpline Number for "+cityName+" is "+Number+"</b>";
            messageBodyPart2.setContent(content1,"text/html; charset=utf-8");
            mimeMultipart.addBodyPart(messageBodyPart);
            mimeMultipart.addBodyPart(messageBodyPart1);
            mimeMultipart.addBodyPart(textMime);
            mimeMultipart.addBodyPart(messageBodyPart2);
            msg.setContent(mimeMultipart);
            Transport.send(msg);
            System.out.println("Email sent successfully");
            foo=true;
        }catch(AddressException e){
            System.out.println("Email service error"+e);
        }catch(MessagingException e) {
            e.printStackTrace();
        }
        return foo;
    }
}
