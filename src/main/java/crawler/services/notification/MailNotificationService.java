package crawler.services.notification;

import crawler.core.usecase.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;

@Component
public class MailNotificationService implements NotificationService<Mail> {

    private static final Logger logger = LoggerFactory.getLogger(MailNotificationService.class);

    @Autowired
    JavaMailSender mailSender;

    @Override

    public void notify(Mail mail) {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws MessagingException {
                    mimeMessage.setRecipients(Message.RecipientType.TO,
                            getDefaultRecipientsFromProperty());
                    mimeMessage.setFrom(new InternetAddress(getDefaultFromUserFromProperty()));
                    mimeMessage.setSubject(mail.getSubject());
                    mimeMessage.setText(mail.getText());
                }
            };
            mailSender.send(preparator);
            logger.info("Mail has been sent:" + getDefaultRecipientsFromProperty());
        } catch (MailException ex) {
            logger.error("Notification service error:" + ex.getMessage());
        }
    }

    private String getDefaultRecipientsFromProperty() {
        return System.getProperty("mail.recipients");
    }

    private String getDefaultFromUserFromProperty() {
        return System.getProperty("mail.from");
    }
}
