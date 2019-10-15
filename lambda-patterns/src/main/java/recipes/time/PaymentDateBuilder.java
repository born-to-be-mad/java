package recipes.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author dzmitry.marudau
 * @since 2019.10
 */
public class PaymentDateBuilder {
    private final Date originalDate;
    private final Date calculatedDate;
    private int daysUntilOEM;

    private PaymentDateBuilder(Builder builder) {
        originalDate = builder.originalDate;
        daysUntilOEM = builder.daysUntilOEM;
        // days * 24 hours * 60 minutes * 60 seconds * 1000ms
        calculatedDate = new Date(originalDate.getTime() + daysUntilOEM * 24 * 60 * 60 * 1000L);
        //java 8
        /*calculatedDate = Date.from(
            asLocalDate()
                .plusDays(daysUntilOEM)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        );*/
    }

    private LocalDate asLocalDate() {
        return new java.sql.Date(originalDate.getTime()).toLocalDate();
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

    public Date getCalculatedDate() {
        return calculatedDate;
    }

    public int getDaysUntilOEM() {
        return daysUntilOEM;
    }

    public static final class Builder {
        private Date originalDate;
        private int daysUntilOEM;

        private Builder() {
        }

        public Builder withOriginalDate(Date val) {
            originalDate = val;
            return this;
        }

        public Builder withDaysUntilEOM(int days) {
            this.daysUntilOEM = days;
            return this;
        }

        public PaymentDateBuilder build() {
            return new PaymentDateBuilder(this);
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
            return new SimpleDateFormat(pattern).format(paymentDateBuilder.getCalculatedDate());
        }

    }
}
