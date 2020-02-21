package crawler.services.notification;

public class Mail {

    private String toUser;
    private String subject;
    private String text;

    public Mail(String toUser, String subject, String text) {
        this.toUser = toUser;
        this.subject = subject;
        this.text = text;
    }

    public Mail(String toUser, String subject) {
        this.toUser = toUser;
        this.subject = subject;
    }

    public Mail(String subject) {
        this.subject = subject;
    }

    public Boolean hasRecipient() {
        return this.toUser != null;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
