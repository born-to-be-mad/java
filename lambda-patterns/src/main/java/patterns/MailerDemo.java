package patterns;

public class MailerDemo {
    public static void main(String[] args) {
        Mailer mailer = new Mailer();
        mailer.from("test@test.com")
                .to("admin@test.com")
                .subject("bad example")
                .body("do not send such ")
                .send();
    }
}

class Mailer {
    Mailer from(String email) {
        System.out.println("with from ..." + email);
        return this;
    }
    Mailer to(String email) {
        System.out.println("with to ..." + email);
        return this;
    }
    Mailer subject(String subject) {
        System.out.println("with subject ..." + subject);
        return this;
    }
    Mailer body(String body) {
        System.out.println("with body ..." + body);
        return this;
    }
    void send() {
        System.out.println("sending...");
    }
}
