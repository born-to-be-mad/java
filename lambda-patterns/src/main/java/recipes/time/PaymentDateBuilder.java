package recipes.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author dzmitry.marudau
 * @since 2019.10
 */
public class PaymentDateBuilder {

    private final Date originalDate;

    private PaymentDateBuilder(Builder builder) {
        originalDate = builder.originalDate;
    }

    public static Builder get() {
        return new Builder();
    }

    public Formatter withFormatter() {
        return new Formatter(this);
    }

    public Date getOriginalDate() {
        return originalDate;
    }


    public static final class Builder {
        private Date originalDate;
        private int days;
        private PaymentDateBuilder paymentDateBuilder;

        private Builder() {
        }

        public Builder withOriginalDate(Date val) {
            originalDate = val;
            return this;
        }

        public Builder withDaysUntilEOM(int days) {
            // days * 24 hours * 60 minutes * 60 seconds * 1000ms
            originalDate.setTime(originalDate.getTime() + days * 24 * 60 * 60 * 1000L);
            return this;
        }

        public Builder withDaysUntilEOMJava8(int days) {
            originalDate = Date.from(
                asLocalDate()
                    .plusDays(days)
                    .atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()
            );
            return this;
        }

        public PaymentDateBuilder build() {
            paymentDateBuilder = new PaymentDateBuilder(this);
            return paymentDateBuilder;
        }

        private LocalDate asLocalDate() {
            return new java.sql.Date(originalDate.getTime()).toLocalDate();
        }

    }

    public static final class Formatter {
        private PaymentDateBuilder paymentDateBuilder;
        private String pattern = "dd.MM.yyyy";

        private Formatter(PaymentDateBuilder paymentDateBuilder) {
            this.paymentDateBuilder = paymentDateBuilder;
        }

        public Formatter withPattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public String format() {
            return new SimpleDateFormat(pattern).format(paymentDateBuilder.getOriginalDate());
        }

    }
}
