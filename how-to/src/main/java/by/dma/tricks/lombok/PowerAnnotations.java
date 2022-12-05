package by.dma.tricks.lombok;

import lombok.Builder;
import lombok.NonNull;
import lombok.With;

/**
 * Lombok annotations work with immutable objects and with the new record type.
 * @With to create new instances of the record, with modified fields.
 * @NonNull which can help us guard against invalid data being passed into the constructor.
 * @Builder annotation work as a sharm.
 *
 * @author dzmitry.marudau
 * @since 2022.12
 */
public class PowerAnnotations {

    public static void main(String[] args) {

        System.out.println("record via factory method: " +
                           new User("james", "bond", "xxx", "agent-007", 7L)
                                   .withEmail("007@gmail.com"));

        System.out.println("record via Lombok: " +
                           new UserLombok("james", "bond", "xxx", "agent-007", 7L)
                                   .withEmail("007@gmail.com"));

        System.out.println("record via Lombok.Builder: " +
                           UserLombok.builder().firstName("james")
                                     .lastName("bond")
                                     .nickName("agent-007")
                                     .id(7L)
                                     .email("007@gmail.com")
                                     .build());
    }
}

record User(

        String firstName,
        String lastName,
        String email,
        String nickName,
        Long id
) {

    User(String firstName, String lastName, String email, String nickName, Long id) {
        if (nickName == null) {
            throw new IllegalArgumentException("Nickname cannot be null!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nickName = nickName;
        this.id = id;
    }

    public User withEmail(String newEmail) {
        return new User(firstName, lastName, newEmail, nickName, id);
    }

    public User withNickname(String newNickname) {
        return new User(firstName, lastName, email, newNickname, id);
    }

    public User withId(Long newId) {
        return new User(firstName, lastName, email, nickName, newId);
    }
}

@Builder
record UserLombok(

        String firstName,
        String lastName,
        @With String email,
        @NonNull  @With String nickName,
        @With Long id
) {

}
