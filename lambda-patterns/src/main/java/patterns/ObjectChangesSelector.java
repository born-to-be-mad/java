package patterns;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * The usage of lambda-functions as lightweight STRATEGY to check that some object properties changed.
 **/
public class ObjectChangesSelector {

    public static void main(String[] args) {

        Map.of(
                   //no changes
                   new DataHolder(true, false), new DataHolder(true, false),
                   //push changed: false -> true
                   new DataHolder(false, false), new DataHolder(true, false),
                   //email changed: true -> false
                   new DataHolder(false, true), new DataHolder(false, false),
                   //email and push changed: false -> true, false -> true
                   new DataHolder(false, false), new DataHolder(true, true)
           )
           .forEach((oldObject, newObject) -> {
                        Function<String, String> emailNotification = userId -> notifyIfEmailChanged(
                                userId, oldObject::isEmailEnabled, newObject::isEmailEnabled
                        );
                        Function<String, String> pushNotification = userId -> notifyIfPushChanged(
                                userId, oldObject::isPushEnabled, newObject::isPushEnabled
                        );

               emailNotification.andThen(pushNotification)
                                //.andThen(ObjectChangesSelector::printDelimiter)
                                .apply("userId");
                        //bulkPublishing(emailNotification.andThen(pushNotification), "userId");
                    }
           );

    }

    private static String printDelimiter(String userId) {
            System.out.println("#".repeat(30));
            return userId;
    }

    public static String notifyIfPushChanged(String userId, Supplier<Boolean> oldValueSupplier,
                                             Supplier<Boolean> newValueSupplier) {
        Boolean oldValue = oldValueSupplier.get();
        Boolean newValue = newValueSupplier.get();
        if (oldValue != newValue) {
            System.out.printf("%s PUSH changed...%s -> %s%n", userId, oldValue, newValue);
        }
        return userId;
    }

    public static String notifyIfEmailChanged(String userId, Supplier<Boolean> oldValueSupplier,
                                              Supplier<Boolean> newValueSupplier) {
        Boolean oldValue = oldValueSupplier.get();
        Boolean newValue = newValueSupplier.get();
        if (oldValue != newValue) {
            System.out.printf("%s E-MAIL changed...%s -> %s%n", userId, oldValue, newValue);
        }
        return userId;
    }

    private static void bulkPublishing(Function<String, String> function, String userId) {
        System.out.println("Publishing...." + function.apply(userId));
    }

}

class DataHolder {

    private final boolean pushEnabled;
    private final boolean emailEnabled;

    DataHolder(boolean pushEnabled, boolean emailEnabled) {
        this.pushEnabled = pushEnabled;
        this.emailEnabled = emailEnabled;
    }

    public boolean isPushEnabled() {
        return pushEnabled;
    }

    public boolean isEmailEnabled() {
        return emailEnabled;
    }
}



