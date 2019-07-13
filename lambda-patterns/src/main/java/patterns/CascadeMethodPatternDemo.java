package patterns;

import java.util.function.Consumer;

public class CascadeMethodPatternDemo {
    public static void main(String[] args) {
        Mailer.send(mailer ->
                mailer.from("test@test.com")
                        .to("admin@test.com")
                        .subject("bad example")
                        .body("do not send such ")
        );
    }
}

class Mailer {
    private Mailer() {}
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
    static void send(Consumer<Mailer> block) {
        Mailer mailer = new Mailer();
        block.accept(mailer);
        System.out.println("sending...");
    }
}
