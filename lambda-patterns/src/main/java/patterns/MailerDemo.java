package patterns;

public class MailerDemo {
    public static void main(String[] args) {
        Mailer mailer = new Mailer();
        mailer.from("test@test.com");
        mailer.send();
    }
}

class Mailer {
    void from(String email) {
        System.out.println("with from ..." + email);
    }
    void to(String email) {
        System.out.println("with to ..." + email);
    }
    void subject(String subject) {
        System.out.println("with subject ..." + subject);
    }
    void body(String body) {
        System.out.println("with body ..." + body);
    }
    void send() {
        System.out.println("sending...");
    }
}
